package com.vapp.service;

import com.vapp.exception.BankException;
import com.vapp.model.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface TransactionService {
    ApiResponse transactionDetails() throws BankException;
}
