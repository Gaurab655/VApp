package VApp.VApp.controller;

import VApp.VApp.dto.requestDto.DebitCreditDto;
import VApp.VApp.dto.requestDto.RegisterAndAccountDto;
import VApp.VApp.entity.Account;
import VApp.VApp.services.AccountServices;
import VApp.VApp.services.UserAndAccountServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userAndAccount")
public class UserAndAccount {
    @Autowired
    private UserAndAccountServices userAndAccountServices;
    @Autowired
    private AccountServices accountServices;

    @PostMapping("/newUser")
    public ResponseEntity<RegisterAndAccountDto> createUser(@RequestBody RegisterAndAccountDto registerAndAccountDto){
        return userAndAccountServices.newUserAndAccount(registerAndAccountDto);
    }

}
