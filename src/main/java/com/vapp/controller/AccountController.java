package com.vapp.controller;

import com.vapp.dto.requestDto.DebitCreditRequestDto;
import com.vapp.dto.requestDto.TransferBalanceRequestDto;
import com.vapp.exception.BankException;
import com.vapp.model.ApiResponse;
import com.vapp.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/credit")
    public ResponseEntity<ApiResponse> credit(@Valid @RequestBody DebitCreditRequestDto debitCreditRequestDto) throws BankException {
        return ResponseEntity.ok(accountService.creditAccount(debitCreditRequestDto));
    }

    @PostMapping("/debit")
    public ResponseEntity<ApiResponse> debit(@Valid @RequestBody DebitCreditRequestDto debitCreditRequestDto) throws BankException {
        return ResponseEntity.ok(accountService.debitAccount(debitCreditRequestDto));
    }

    @PostMapping("/transferAmount")
    public ResponseEntity<ApiResponse> transfer(@Valid @RequestBody TransferBalanceRequestDto transferBalanceRequestDto) throws BankException {
        return ResponseEntity.ok(accountService.transferAmount(transferBalanceRequestDto));
    }

    @GetMapping("/check-balance")
    public ResponseEntity<ApiResponse> checkBalance() {
        return ResponseEntity.ok(accountService.checkBalance());
    }
}
