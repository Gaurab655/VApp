package VApp.VApp.initializer;

import VApp.VApp.entity.Account;
import VApp.VApp.entity.BankAccount;
import VApp.VApp.repository.BankAccountRepository;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class ConstBankAccount {
    private final BankAccountRepository bankAccountRepository;

    public void createBankAccount(){
        if (bankAccountRepository.findById(1).isEmpty()){
            BankAccount bankAccount = new BankAccount();
            bankAccount.setAccountNumber(1234565789L);
            bankAccount.setName("Global Ime Bank Ltd");
            bankAccount.setBalance(0.0);
            bankAccountRepository.save(bankAccount);
            System.out.println("Bank account created successfully");
        }
    }
}
