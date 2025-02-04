package com.vapp.service.impl;

import com.vapp.builder.ServiceResponseBuilder;
import com.vapp.dto.requestDto.ServiceChargeRequestDto;
import com.vapp.dto.responseDto.UserResponseDto;
import com.vapp.entity.ServiceChargeEntity;
import com.vapp.entity.UserEntity;
import com.vapp.exception.BankException;
import com.vapp.model.ApiResponse;
import com.vapp.repository.ServiceChargeRepository;
import com.vapp.repository.UserRepository;
import com.vapp.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final ServiceChargeRepository serviceChargeRepository;


    @Override
    public ApiResponse insertServiceCharge(ServiceChargeRequestDto serviceChargeRequestDto) throws BankException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ServiceChargeEntity serviceChargeEntity = this.modelMapper.map(serviceChargeRequestDto, ServiceChargeEntity.class);
        serviceChargeRepository.save(serviceChargeEntity);
        return ServiceResponseBuilder.buildFailedBuilder("Inserted successfully");

    }

    @Override
    public ApiResponse getUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        List<UserResponseDto> userResponseDto = userEntities.stream()
                .map(user -> modelMapper.map(user, UserResponseDto.class))
                .toList();
        return ServiceResponseBuilder.buildSuccessBuilder(" List of users ", userResponseDto);
    }

}
