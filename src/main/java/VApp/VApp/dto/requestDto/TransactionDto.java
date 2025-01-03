package VApp.VApp.dto.requestDto;

import VApp.VApp.entity.BankAccount;
import VApp.VApp.entity.Transaction;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class TransactionDto {
    private int id;
    private LocalDateTime dateTime;
    private String transactionType;
    private double amount;
    private double serviceCharge;
    private double totalAmount;
    private String status;
    private Long sendingAccount;
    private BankAccount beneficiaryAccount;

    public TransactionDto(Transaction transaction) {
        this.id = transaction.getId();
        this.dateTime = transaction.getDateTime();
        this.transactionType = transaction.getTransactionType();
        this.amount = transaction.getAmount();
        this.serviceCharge = transaction.getServiceCharge();
        this.totalAmount = transaction.getTotalAmount();
        this.status = transaction.getStatus();
        this.sendingAccount = transaction.getSenderAccount();

        if (transaction.getBeneficiaryAccount() != null) {
            this.beneficiaryAccount = new BankAccount(transaction.getBeneficiaryAccount());
        }
    }
}
