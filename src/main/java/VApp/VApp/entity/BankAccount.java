package VApp.VApp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bank")
public class BankAccount {
    @Id
    private int id;
    private String name ;
    private double balance;
    private Long accountNumber;


    public BankAccount(Account account) {
        this.id=account.getId();
        this.accountNumber = account.getAccountNumber();
        this.name=account.getFullName();
        this.balance=account.getBalance();
    }
}
