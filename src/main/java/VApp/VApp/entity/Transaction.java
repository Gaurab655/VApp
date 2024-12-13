package VApp.VApp.entity;

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
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime dateTime;
    private String transactionType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "beneficiary_account", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private Account beneficiaryAccount;

    private double amount;
    private double serviceCharge;
    private double totalAmount;
    private String status;
}
