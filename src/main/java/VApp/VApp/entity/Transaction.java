package VApp.VApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
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
    private Account beneficiaryAccount;

}
