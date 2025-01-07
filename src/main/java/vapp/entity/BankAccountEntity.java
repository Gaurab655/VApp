package vapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bank")
public class BankAccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private BigDecimal balance;
    private Long accountNumber;


    public BankAccountEntity(AccountEntity accountEntity) {
        this.id = accountEntity.getId();
        this.accountNumber = accountEntity.getAccountNumber();
        this.name = accountEntity.getFullName();
        this.balance = accountEntity.getBalance();
    }
}
