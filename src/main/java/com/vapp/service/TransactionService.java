package com.vapp.service;

import org.springframework.http.ResponseEntity;
import com.vapp.exception.BankException;

public interface TransactionService {
    ResponseEntity<?> transactionDetails() throws BankException;

}
