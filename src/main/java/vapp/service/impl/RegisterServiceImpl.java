package vapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vapp.dto.requestDto.CreateAccountDto;
import vapp.entity.AccountEntity;
import vapp.entity.UserEntity;
import vapp.exception.BankException;
import vapp.repository.AccountRepository;
import vapp.repository.UserRepository;
import vapp.service.RegisterService;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<String> newUserAndAccount(CreateAccountDto createAccountDto) throws BankException {
        if (userRepository.existsByEmail(createAccountDto.getEmail())) {
            throw new BankException("Enter different email", HttpStatus.BAD_REQUEST);
        }
        UserEntity userEntity = this.modelMapper.map(createAccountDto, UserEntity.class);

        userEntity.setPassword(passwordEncoder.encode(createAccountDto.getPassword()));

        AccountEntity accountEntity = this.modelMapper.map(createAccountDto, AccountEntity.class);

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
