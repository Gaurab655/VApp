package com.vapp.service;

import com.vapp.dto.requestDto.ServiceChargeRequestDto;
import com.vapp.dto.responseDto.UserResponseDto;
import com.vapp.exception.BankException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdminService {
    ResponseEntity<ServiceChargeRequestDto> insertServiceCharge(ServiceChargeRequestDto serviceChargeRequestDto) throws BankException;

    List<UserResponseDto> getUsers();

    void deleteUser(int id) throws BankException;

}
