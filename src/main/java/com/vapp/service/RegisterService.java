package com.vapp.service;


import com.vapp.dto.requestDto.CreateAccountRequestDto;
import com.vapp.exception.BankException;
import com.vapp.model.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface RegisterService {
    ApiResponse newUserAndAccount(CreateAccountRequestDto createAccountRequestDto) throws BankException;
}
