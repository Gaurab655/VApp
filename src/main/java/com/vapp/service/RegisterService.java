package com.vapp.service;


import org.springframework.http.ResponseEntity;
import com.vapp.dto.requestDto.CreateAccountRequestDto;
import com.vapp.exception.BankException;

public interface RegisterService {
    ResponseEntity<String> newUserAndAccount(CreateAccountRequestDto createAccountRequestDto) throws BankException;
}
