package VApp.VApp.controller;

import VApp.VApp.dto.requestDto.ServiceChargeDto;
import VApp.VApp.dto.responseDto.UserResponseDto;
import VApp.VApp.exception.BankException;
import VApp.VApp.service.AdminService;
import VApp.VApp.service.TransactionService;
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

    @GetMapping("/all-Users")
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
        return adminService.getUsers();
    }

    @PostMapping
    public ResponseEntity<ServiceChargeDto> serviceCharges (@RequestBody ServiceChargeDto serviceChargeDto) throws BankException {
      return adminService.insertServiceCharge(serviceChargeDto);
    }

    @GetMapping("/check-transaction")
    public ResponseEntity<?> checkTransaction() throws BankException {
       return transactionService.transactionDetails();
    }
}
