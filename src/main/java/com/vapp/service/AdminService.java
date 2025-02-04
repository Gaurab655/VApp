package com.vapp.service;

import com.vapp.dto.requestDto.ServiceChargeRequestDto;
import com.vapp.dto.responseDto.UserResponseDto;
import com.vapp.exception.BankException;
import com.vapp.model.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdminService {
    ApiResponse insertServiceCharge(ServiceChargeRequestDto serviceChargeRequestDto) throws BankException;

    ApiResponse getUsers();
}
