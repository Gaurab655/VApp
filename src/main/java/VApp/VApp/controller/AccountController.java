package VApp.VApp.controller;

import VApp.VApp.dto.DebitCreditDto;
import VApp.VApp.entity.User;
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

    @PostMapping
    public ResponseEntity<Account> openAccount(@RequestBody Account account){
      return accountServices.createAccount(account);
    }

    @GetMapping
    public List<Account> getAccounts(){
        return accountServices.getAccounts();
    }

    @PostMapping("/credit")
    public ResponseEntity<?> credit(@RequestBody DebitCreditDto debitCreditDto) {
        return accountServices.creditAccount(debitCreditDto);
    }

}
