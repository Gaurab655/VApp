package vapp.service;

import org.springframework.http.ResponseEntity;
import vapp.exception.BankException;

public interface TransactionService {
    ResponseEntity<?> transactionDetails() throws BankException;

}
