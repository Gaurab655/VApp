package com.vapp.controller;

import com.vapp.dto.requestDto.DebitCreditRequestDto;
import com.vapp.dto.requestDto.TransferBalanceRequestDto;
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
    public ResponseEntity<String> credit(@Valid @RequestBody DebitCreditRequestDto debitCreditRequestDto) throws Exception {
        return accountService.creditAccount(debitCreditRequestDto);
    }

    @PostMapping("/debit")
    public ResponseEntity<String> debit(@Valid @RequestBody DebitCreditRequestDto debitCreditRequestDto) throws Exception {
        return accountService.debitAccount(debitCreditRequestDto);
    }

    @PostMapping("/transferAmount")
    public ResponseEntity<String> transfer(@Valid @RequestBody TransferBalanceRequestDto transferBalanceRequestDto) throws Exception {
        return accountService.transferAmount(transferBalanceRequestDto);
    }

    @GetMapping("/check-balance")
    public ResponseEntity<String> checkBalance() {
        return accountService.checkBalance();
    }
}
