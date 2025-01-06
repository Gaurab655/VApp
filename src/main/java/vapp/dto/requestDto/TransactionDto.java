package vapp.dto.requestDto;

import vapp.entity.BankAccountEntity;
import vapp.entity.TransactionEntity;
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
    private BankAccountEntity beneficiaryAccount;

    public TransactionDto(TransactionEntity transactionEntity) {
        this.id = transactionEntity.getId();
        this.dateTime = transactionEntity.getDateTime();
        this.transactionType = transactionEntity.getTransactionType();
        this.amount = transactionEntity.getAmount();
        this.serviceCharge = transactionEntity.getServiceCharge();
        this.totalAmount = transactionEntity.getTotalAmount();
        this.status = transactionEntity.getStatus();
        this.sendingAccount = transactionEntity.getSenderAccount();

        if (transactionEntity.getBeneficiaryAccountEntity() != null) {
            this.beneficiaryAccount = new BankAccountEntity(transactionEntity.getBeneficiaryAccountEntity());
        }
    }
}
