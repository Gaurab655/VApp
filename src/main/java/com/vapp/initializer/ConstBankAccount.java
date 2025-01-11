package com.vapp.initializer;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.vapp.entity.BankAccountEntity;
import com.vapp.repository.BankAccountRepository;

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
