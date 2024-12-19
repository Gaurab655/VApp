package VApp.VApp.controller;

import VApp.VApp.dto.requestDto.RegisterAndAccountDto;
import VApp.VApp.service.UserAndAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userAndAccount")
@RequiredArgsConstructor
public class UserAndAccount {

    private final UserAndAccountService userAndAccountService;

    @PostMapping("/newUser")
    public ResponseEntity<RegisterAndAccountDto> createUser(@Valid @RequestBody RegisterAndAccountDto registerAndAccountDto){
        return userAndAccountService.newUserAndAccount(registerAndAccountDto);
    }
}
