package vapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transaction")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime dateTime;
    private String transactionType;
    private double amount;
    private double serviceCharge;
    private double totalAmount;
    private String status;
    private Long senderAccount;
    @ManyToOne
    @JoinColumn(name = "beneficiary_account", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private AccountEntity beneficiaryAccountEntity;

}
