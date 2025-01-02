package VApp.VApp.controller;

import VApp.VApp.dto.requestDto.RegisterAndAccountDto;
import VApp.VApp.exception.BankException;
import VApp.VApp.service.UserAndAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userAndAccount")
@RequiredArgsConstructor
public class UserAndAccount {
    private final UserAndAccountService userAndAccountService;

    @PostMapping("/newUser")
    public ResponseEntity<String> createUser(@Valid @RequestBody RegisterAndAccountDto registerAndAccountDto) throws BankException {
        return userAndAccountService.newUserAndAccount(registerAndAccountDto);
    }
}
