package vapp.initializer;

import vapp.entity.BankAccountEntity;
import vapp.repository.BankAccountRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class ConstBankAccount {
    private final BankAccountRepository bankAccountRepository;

    @PostConstruct
    public void createBankAccount() {
        if (bankAccountRepository.findAll().isEmpty()) {
            BankAccountEntity bankAccountEntity = new BankAccountEntity();
            bankAccountEntity.setId(1);
            bankAccountEntity.setAccountNumber(1234565789L);
            bankAccountEntity.setName("Global Ime Bank Ltd");
            bankAccountEntity.setBalance(new BigDecimal(0));
            bankAccountRepository.save(bankAccountEntity);
            System.out.println("Bank account created successfully");
        }
    }
}
