package vapp.controller;

import vapp.dto.requestDto.DebitCreditDto;
import vapp.dto.requestDto.TransferBalanceDto;
import vapp.service.AccountService;
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
    public ResponseEntity<String> credit(@Valid @RequestBody DebitCreditDto debitCreditDto) throws Exception {
        return accountService.creditAccount(debitCreditDto);
    }

    @PostMapping("/debit")
    public ResponseEntity<?> debit(@Valid @RequestBody DebitCreditDto debitCreditDto) throws Exception {
        return accountService.debitAccount(debitCreditDto);
    }

    @PostMapping("/transferAmount")
    public ResponseEntity<?> transfer(@Valid @RequestBody TransferBalanceDto transferBalanceDto) throws Exception {
        return  accountService.transferAmount(transferBalanceDto);
    }

    @GetMapping("/check-balance")
    public ResponseEntity<?> checkBalance() {
        return accountService.checkBalance();
    }
}
