package VApp.VApp.initializer;

import VApp.VApp.entity.BankAccount;
import VApp.VApp.repository.BankAccountRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConstBankAccount {
    private final BankAccountRepository bankAccountRepository;

    @PostConstruct
    public void createBankAccount() {
        if (bankAccountRepository.findAll().isEmpty()) {
            BankAccount bankAccount = new BankAccount();
            bankAccount.setId(1);
            bankAccount.setAccountNumber(1234565789L);
            bankAccount.setName("Global Ime Bank Ltd");
            bankAccount.setBalance(0.0);
            bankAccountRepository.save(bankAccount);
            System.out.println("Bank account created successfully");
        }
    }
}
