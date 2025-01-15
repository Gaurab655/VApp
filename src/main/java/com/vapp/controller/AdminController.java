package com.vapp.controller;

import com.vapp.dto.requestDto.ServiceChargeRequestDto;
import com.vapp.dto.requestDto.TransactionRequestDto;
import com.vapp.dto.responseDto.UserResponseDto;
import com.vapp.exception.BankException;
import com.vapp.service.AdminService;
import com.vapp.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final TransactionService transactionService;
    private final AdminService adminService;

    @GetMapping("/all-users")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(adminService.getUsers());
    }

    @PostMapping
    public ResponseEntity<ServiceChargeRequestDto> serviceCharges(@RequestBody ServiceChargeRequestDto serviceChargeRequestDto) throws BankException {
        return adminService.insertServiceCharge(serviceChargeRequestDto);
    }

    @GetMapping("/check-transaction")
    public ResponseEntity<Object> checkTransaction() throws BankException {
        return ResponseEntity.ok(transactionService.transactionDetails());
    }
}