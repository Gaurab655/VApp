package VApp.VApp.controller;

import VApp.VApp.dto.DebitCreditDto;
import VApp.VApp.services.AccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accountManager")
public class AccountManageController {
    @Autowired
    private AccountManager accountManager;
    @Autowired
    private UserController userController;

@PostMapping("/debit")
    public void debitMoney(@RequestBody DebitCreditDto debitCreditDto){

    }
}
