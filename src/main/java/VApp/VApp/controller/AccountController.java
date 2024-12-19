package VApp.VApp.controller;

import VApp.VApp.dto.requestDto.DebitCreditDto;
import VApp.VApp.dto.requestDto.TransferBalanceDto;
import VApp.VApp.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/credit")
    public ResponseEntity<?> credit(@RequestBody DebitCreditDto debitCreditDto) throws Exception {
        return accountService.creditAccount(debitCreditDto);
    }
    @PostMapping("/debit")
    public ResponseEntity<?> debit(@RequestBody DebitCreditDto debitCreditDto) throws Exception{
        return accountService.debitAccount(debitCreditDto);
    }
    @PostMapping("/transferAmount")
    public ResponseEntity<?> transfer(@RequestBody TransferBalanceDto transferBalanceDto) throws Exception{
        return accountService.transferAmount(transferBalanceDto);
    }
    @GetMapping("/check-balance")
    public ResponseEntity<?> checkBalance() throws Exception{
        return accountService.checkBalance();
    }


}
