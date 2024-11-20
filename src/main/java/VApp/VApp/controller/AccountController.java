package VApp.VApp.controller;

import VApp.VApp.services.AccountServices;
import VApp.VApp.entity.AccountEntity;
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
    public ResponseEntity<AccountEntity> openAccount(@RequestBody AccountEntity accountEntity){
      return accountServices.createAccount(accountEntity);
    }

    @GetMapping
    public List<AccountEntity> getAccounts(){
        return accountServices.getAccounts();
    }

}
