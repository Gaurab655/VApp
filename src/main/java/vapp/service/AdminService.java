package vapp.service;

import org.springframework.http.ResponseEntity;
import vapp.dto.requestDto.ServiceChargeDto;
import vapp.dto.responseDto.UserResponseDto;
import vapp.exception.BankException;

import java.util.List;

public interface AdminService {
    ResponseEntity<ServiceChargeDto> insertServiceCharge(ServiceChargeDto serviceChargeDto) throws BankException;

    List<UserResponseDto> getUsers();

}
