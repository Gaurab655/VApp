package VApp.VApp.service;

import VApp.VApp.dto.requestDto.RegisterAndAccountDto;
import VApp.VApp.entity.Account;
import VApp.VApp.entity.User;
import VApp.VApp.repository.AccountRepository;
import VApp.VApp.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAndAccountServices {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public ResponseEntity<RegisterAndAccountDto> newUserAndAccount(RegisterAndAccountDto registerAndAccountDto) {
        try {

            User user = this.modelMapper.map(registerAndAccountDto, User.class);
            user.setPassword(passwordEncoder.encode(registerAndAccountDto.getPassword()));

            Account account = this.modelMapper.map(registerAndAccountDto, Account.class);

            account.setUser(user);
            user.setAccount(account);

            Long nextAccountNumber = generateNextAccountNumber();
            account.setAccountNumber(nextAccountNumber);

            userRepository.save(user);
            // automatically saves account coz we are using cascade
//            accountRepository.save(account);

            return new ResponseEntity<>(registerAndAccountDto, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    private Long generateNextAccountNumber() {
        Long lastAccountNumber = accountRepository.findMaxAccountNumber().orElse(65500L);
        return lastAccountNumber + 1;
    }
}
