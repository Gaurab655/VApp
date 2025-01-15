package com.vapp.dto.requestDto;

import com.vapp.entity.BankAccountEntity;
import lombok.Getter;
import lombok.Setter;

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
}
