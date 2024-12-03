package VApp.VApp.controller;

import VApp.VApp.dto.requestDto.RegisterAndAccountDto;
import VApp.VApp.service.AccountServices;
import VApp.VApp.service.UserAndAccountServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userAndAccount")
public class UserAndAccount {

    private final UserAndAccountServices userAndAccountServices;

    UserAndAccount(UserAndAccountServices userAndAccountServices){
        this.userAndAccountServices=userAndAccountServices;
    }

    @PostMapping("/newUser")
    public ResponseEntity<RegisterAndAccountDto> createUser(
            @RequestBody RegisterAndAccountDto registerAndAccountDto){
        return userAndAccountServices.newUserAndAccount(registerAndAccountDto);
    }
}
