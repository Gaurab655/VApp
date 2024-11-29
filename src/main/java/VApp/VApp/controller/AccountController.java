package VApp.VApp.controller;

import VApp.VApp.dto.requestDto.DebitCreditDto;
import VApp.VApp.dto.requestDto.TransferBalanceDto;
import VApp.VApp.services.AccountServices;
import VApp.VApp.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountServices accountServices;

    @PostMapping("create-account")
    public ResponseEntity<Account> openAccount(@RequestBody Account account){
      return accountServices.createAccount(account);
    }
    @PostMapping("/credit")
    public ResponseEntity<?> credit(@RequestBody DebitCreditDto debitCreditDto){
        return accountServices.creditAccount(debitCreditDto);
    }
    @PostMapping("/debit")
    public ResponseEntity<?> debit(@RequestBody DebitCreditDto debitCreditDto){
        return accountServices.debitAccount(debitCreditDto);
    }
    @PostMapping("/transferAmount")
    public ResponseEntity<?> transfer(@RequestBody TransferBalanceDto transferBalanceDto){
        return accountServices.transferAmount(transferBalanceDto);
    }
    @GetMapping("/check-balance")
    public ResponseEntity<?> checkBalance(@RequestBody DebitCreditDto debitCreditDto){
        return accountServices.checkBalance(debitCreditDto);
    }

}
