package com.vapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.vapp.dto.requestDto.CreateAccountRequestDto;
import com.vapp.entity.AccountEntity;
import com.vapp.entity.UserEntity;
import com.vapp.exception.BankException;
import com.vapp.repository.AccountRepository;
import com.vapp.repository.UserRepository;
import com.vapp.service.RegisterService;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<String> newUserAndAccount(CreateAccountRequestDto createAccountRequestDto) throws BankException {
        if (userRepository.existsByEmail(createAccountRequestDto.getEmail())) {
            throw new BankException("Enter different email", HttpStatus.BAD_REQUEST);
        }
        UserEntity userEntity = this.modelMapper.map(createAccountRequestDto, UserEntity.class);

        userEntity.setPassword(passwordEncoder.encode(createAccountRequestDto.getPassword()));

        AccountEntity accountEntity = this.modelMapper.map(createAccountRequestDto, AccountEntity.class);

        accountEntity.setUser(userEntity);
        userEntity.setAccount(accountEntity);

        Long nextAccountNumber = generateNextAccountNumber();
        accountEntity.setAccountNumber(nextAccountNumber);

        userRepository.save(userEntity);
        return new ResponseEntity<>("Account created Successfully", HttpStatus.CREATED);
    }

    private Long generateNextAccountNumber() {
        Long lastAccountNumber = accountRepository.findMaxAccountNumber().orElse(65500L);
        return lastAccountNumber + 1;
    }
}
