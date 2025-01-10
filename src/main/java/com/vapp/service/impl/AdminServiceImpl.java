package com.vapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.vapp.dto.requestDto.ServiceChargeRequestDto;
import com.vapp.dto.responseDto.UserResponseDto;
import com.vapp.entity.ServiceChargeEntity;
import com.vapp.entity.UserEntity;
import com.vapp.exception.BankException;
import com.vapp.repository.ServiceChargeRepository;
import com.vapp.repository.UserRepository;
import com.vapp.service.AdminService;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final ServiceChargeRepository serviceChargeRepository;


    @Override
    public ResponseEntity<ServiceChargeRequestDto> insertServiceCharge(ServiceChargeRequestDto serviceChargeRequestDto) throws BankException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        UserEntity admin = userRepository.findByEmail(email);
        if (admin != null) {
            ServiceChargeEntity serviceChargeEntity = this.modelMapper.map(serviceChargeRequestDto, ServiceChargeEntity.class);
            serviceChargeRepository.save(serviceChargeEntity);
            return new ResponseEntity<>(serviceChargeRequestDto, HttpStatus.CREATED);
        }
        return null;
    }

    @Override
    public List<UserResponseDto> getUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream().map(user -> modelMapper.map(user, UserResponseDto.class)).toList();
    }

    @Override
    public void deleteUser(int id) throws BankException {
       boolean isExists = userRepository.existsById(id);
       if (isExists){
           userRepository.deleteById(id);
       }else {
           throw new BankException("User not found with this id",HttpStatus.UNPROCESSABLE_ENTITY);
       }
    }
}
