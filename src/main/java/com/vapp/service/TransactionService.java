package com.vapp.service;

import com.vapp.dto.requestDto.TransactionRequestDto;
import com.vapp.entity.TransactionEntity;
import org.springframework.http.ResponseEntity;
import com.vapp.exception.BankException;

public interface TransactionService {
    ResponseEntity<Object> transactionDetails() throws BankException;

}
