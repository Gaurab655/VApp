package com.vapp.service.impl;

import com.vapp.builder.ServiceResponseBuilder;
import com.vapp.dto.requestDto.CreateAccountRequestDto;
import com.vapp.entity.AccountEntity;
import com.vapp.entity.UserEntity;
import com.vapp.exception.BankException;
import com.vapp.model.ApiResponse;
import com.vapp.repository.AccountRepository;
import com.vapp.repository.UserRepository;
import com.vapp.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public ApiResponse newUserAndAccount(CreateAccountRequestDto createAccountRequestDto) throws BankException {
        if (userRepository.existsByEmail(createAccountRequestDto.getEmail())) {
            throw new BankException("Enter different email", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        UserEntity userEntity = this.modelMapper.map(createAccountRequestDto, UserEntity.class);

        userEntity.setPassword(passwordEncoder.encode(createAccountRequestDto.getPassword()));

        AccountEntity accountEntity = this.modelMapper.map(createAccountRequestDto, AccountEntity.class);

        accountEntity.setUser(userEntity);
        userEntity.setAccount(accountEntity);

        Long nextAccountNumber = generateNextAccountNumber();
        accountEntity.setAccountNumber(nextAccountNumber);

        userRepository.save(userEntity);
        return ServiceResponseBuilder.buildSuccessBuilder("Account created");
    }

    private Long generateNextAccountNumber() {
        Long lastAccountNumber = accountRepository.findMaxAccountNumber().orElse(65500L);
        return lastAccountNumber + 1;
    }
}
