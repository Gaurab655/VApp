package vapp.service;


import org.springframework.http.ResponseEntity;
import vapp.dto.requestDto.CreateAccountDto;
import vapp.exception.BankException;

public interface RegisterService {
    ResponseEntity<String> newUserAndAccount(CreateAccountDto createAccountDto) throws BankException;
}
