package VApp.VApp.controller;

import VApp.VApp.dto.RegisterAndAccountDto;
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

    @PostMapping("/newUser")
    public ResponseEntity<RegisterAndAccountDto> createUser(@RequestBody RegisterAndAccountDto registerAndAccountDto){
        return userAndAccountServices.newUserAndAccount(registerAndAccountDto);
    }
}
