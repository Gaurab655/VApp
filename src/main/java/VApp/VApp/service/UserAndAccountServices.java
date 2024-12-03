package VApp.VApp.service;

import VApp.VApp.dto.requestDto.RegisterAndAccountDto;
import VApp.VApp.entity.Account;
import VApp.VApp.entity.User;
import VApp.VApp.repository.AccountRepository;
import VApp.VApp.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAndAccountServices {


    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    UserAndAccountServices(AccountRepository accountRepository,
                           UserRepository userRepository,
                           ModelMapper modelMapper,
                           PasswordEncoder passwordEncoder){
        this.accountRepository=accountRepository;
        this.userRepository=userRepository;
        this.modelMapper=modelMapper;
        this.passwordEncoder=passwordEncoder;
    }

    @Transactional
    public ResponseEntity<RegisterAndAccountDto> newUserAndAccount(RegisterAndAccountDto registerAndAccountDto){
        try {
            User user = this.modelMapper.map(registerAndAccountDto, User.class);
            user.setPassword(passwordEncoder.encode(registerAndAccountDto.getPassword()));
            User saveUser = userRepository.save(user);

            Account account = this.modelMapper.map(registerAndAccountDto,Account.class);
            account.setUser(saveUser);
            accountRepository.save(account);
            user.setAccount(account);

            return new ResponseEntity<>(registerAndAccountDto, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
