package com.vapp.dto.requestDto;

import lombok.Getter;
import lombok.Setter;
import com.vapp.entity.BankAccountEntity;
import com.vapp.entity.TransactionEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class TransactionRequestDto {
    private int id;
    private LocalDateTime dateTime;
    private String transactionType;
    private BigDecimal amount;
    private double serviceCharge;
    private BigDecimal totalAmount;
    private String status;
    private Long sendingAccount;
    private BankAccountEntity receiverAccount;

    public TransactionRequestDto(TransactionEntity transactionEntity) {
        this.id = transactionEntity.getId();
        this.dateTime = transactionEntity.getDateTime();
        this.transactionType = transactionEntity.getTransactionType();
        this.amount = BigDecimal.valueOf(transactionEntity.getAmount());
        this.serviceCharge = transactionEntity.getServiceCharge();
        this.totalAmount = transactionEntity.getTotalAmount();
        this.status = transactionEntity.getStatus();
        this.sendingAccount = transactionEntity.getSenderAccount();

        if (transactionEntity.getReceiverAccount() != null) {
            this.receiverAccount = new BankAccountEntity(transactionEntity.getReceiverAccount());
        }
    }
}
