package vapp.controller;

import vapp.dto.requestDto.CreateAccountDto;
import vapp.exception.BankException;
import vapp.service.RegisterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class CreateAccountController {
    private final RegisterService registerService;

    @PostMapping("/newUser")
    public ResponseEntity<String> createUser(@Valid @RequestBody CreateAccountDto createAccountDto) throws BankException {
        return registerService.newUserAndAccount(createAccountDto);
    }
}
