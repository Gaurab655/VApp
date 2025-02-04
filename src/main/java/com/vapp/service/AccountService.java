package com.vapp.service;

import com.vapp.dto.requestDto.DebitCreditRequestDto;
import com.vapp.dto.requestDto.TransferBalanceRequestDto;
import com.vapp.exception.BankException;
import com.vapp.model.ApiResponse;

public interface AccountService {
    ApiResponse creditAccount(DebitCreditRequestDto debitCreditRequestDto) throws BankException;

    ApiResponse debitAccount(DebitCreditRequestDto debitCreditRequestDto) throws BankException;

    ApiResponse transferAmount(TransferBalanceRequestDto transferBalanceRequestDto) throws BankException;

    ApiResponse checkBalance();
}
