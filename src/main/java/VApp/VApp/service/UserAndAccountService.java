package VApp.VApp.service;

import VApp.VApp.dto.requestDto.RegisterAndAccountDto;
import VApp.VApp.entity.Account;
import VApp.VApp.entity.User;
import VApp.VApp.exception.BankException;
import VApp.VApp.repository.AccountRepository;
import VApp.VApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAndAccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public ResponseEntity<String> newUserAndAccount(RegisterAndAccountDto registerAndAccountDto) throws BankException {
        if (userRepository.existsByEmail(registerAndAccountDto.getEmail())) {
            throw new BankException("Enter different email", HttpStatus.BAD_REQUEST);
        }
        User user = this.modelMapper.map(registerAndAccountDto, User.class);

        user.setPassword(passwordEncoder.encode(registerAndAccountDto.getPassword()));

        Account account = this.modelMapper.map(registerAndAccountDto, Account.class);

        account.setUser(user);
        user.setAccount(account);

        Long nextAccountNumber = generateNextAccountNumber();
        account.setAccountNumber(nextAccountNumber);

        userRepository.save(user);
        return new ResponseEntity<>("Account created Successfully", HttpStatus.CREATED);
    }

    private Long generateNextAccountNumber() {
        Long lastAccountNumber = accountRepository.findMaxAccountNumber().orElse(65500L);
        return lastAccountNumber + 1;
    }
}
