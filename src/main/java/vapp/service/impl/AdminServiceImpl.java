package vapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import vapp.dto.requestDto.ServiceChargeDto;
import vapp.dto.responseDto.UserResponseDto;
import vapp.entity.ServiceChargeEntity;
import vapp.entity.UserEntity;
import vapp.exception.BankException;
import vapp.repository.ServiceChargeRepository;
import vapp.repository.UserRepository;
import vapp.service.AccountService;
import vapp.service.AdminService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final ServiceChargeRepository serviceChargeRepository;


    @Override
    public ResponseEntity<ServiceChargeDto> insertServiceCharge(ServiceChargeDto serviceChargeDto) throws BankException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        UserEntity admin = userRepository.findByEmail(email).orElseThrow(() -> new BankException("No admin found", HttpStatus.NOT_FOUND));
        if (admin != null) {
            ServiceChargeEntity serviceChargeEntity = this.modelMapper.map(serviceChargeDto, ServiceChargeEntity.class);
            serviceChargeRepository.save(serviceChargeEntity);
            return new ResponseEntity<>(serviceChargeDto, HttpStatus.CREATED);
        }
        return null;
    }

    @Override
    public List<UserResponseDto> getUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream().map((user) -> UserResponseDto.fromEntity(user, modelMapper)).toList();
    }
}
