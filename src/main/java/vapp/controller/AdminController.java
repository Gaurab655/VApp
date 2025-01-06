package vapp.controller;

import vapp.dto.requestDto.ServiceChargeDto;
import vapp.dto.responseDto.UserResponseDto;
import vapp.exception.BankException;
import vapp.service.AdminService;
import vapp.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    private final TransactionService transactionService;

    @GetMapping("/all-users")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(adminService.getUsers());
    }

    @PostMapping
    public ResponseEntity<ServiceChargeDto> serviceCharges(@RequestBody ServiceChargeDto serviceChargeDto) throws BankException {
        return adminService.insertServiceCharge(serviceChargeDto);
    }

    @GetMapping("/check-transaction")
    public ResponseEntity<?> checkTransaction() throws BankException {
        return transactionService.transactionDetails();
    }
}