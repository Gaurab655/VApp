package VApp.VApp.entity;

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
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private BigDecimal balance;
    private Long accountNumber;


    public BankAccount(Account account) {
        this.id = account.getId();
        this.accountNumber = account.getAccountNumber();
        this.name = account.getFullName();
        this.balance = account.getBalance();
    }
}
