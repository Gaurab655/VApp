package com.vapp.service;

import org.springframework.http.ResponseEntity;
import com.vapp.dto.requestDto.DebitCreditRequestDto;
import com.vapp.dto.requestDto.TransferBalanceRequestDto;
import com.vapp.exception.BankException;

public interface AccountService {
    ResponseEntity<String> creditAccount(DebitCreditRequestDto debitCreditRequestDto) throws Exception;

    ResponseEntity<String> debitAccount(DebitCreditRequestDto debitCreditRequestDto) throws Exception;

    ResponseEntity<String> transferAmount(TransferBalanceRequestDto transferBalanceRequestDto) throws BankException;

    ResponseEntity<String> checkBalance();


}
