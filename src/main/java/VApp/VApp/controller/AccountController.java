package VApp.VApp.controller;

import VApp.VApp.dto.requestDto.DebitCreditDto;
import VApp.VApp.dto.requestDto.TransferBalanceDto;
import VApp.VApp.service.AccountServices;
import VApp.VApp.entity.Account;
import jdk.jfr.StackTrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountServices accountServices;

    AccountController(AccountServices accountServices){
        this.accountServices=accountServices;
    }

    @PostMapping("/credit")
    public ResponseEntity<?> credit(@RequestBody DebitCreditDto debitCreditDto) throws Exception {
        return accountServices.creditAccount(debitCreditDto);
    }
    @PostMapping("/debit")
    public ResponseEntity<?> debit(@RequestBody DebitCreditDto debitCreditDto) throws Exception{
        return accountServices.debitAccount(debitCreditDto);
    }
    @PostMapping("/transferAmount")
    public ResponseEntity<?> transfer(@RequestBody TransferBalanceDto transferBalanceDto) throws Exception{
        return accountServices.transferAmount(transferBalanceDto);
    }
    @GetMapping("/check-balance")
    public ResponseEntity<?> checkBalance() throws Exception{
        return accountServices.checkBalance();
    }

}
