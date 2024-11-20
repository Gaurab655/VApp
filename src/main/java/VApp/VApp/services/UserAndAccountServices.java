package VApp.VApp.services;

import VApp.VApp.dto.RegisterAndAccountDto;
import VApp.VApp.entity.AccountEntity;
import VApp.VApp.entity.UserEntity;
import VApp.VApp.repository.AccountRepository;
import VApp.VApp.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserAndAccountServices {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public ResponseEntity<RegisterAndAccountDto> newUserAndAccount(RegisterAndAccountDto registerAndAccountDto){
        try {
            UserEntity userEntity = new UserEntity();
            userEntity.setEmail(registerAndAccountDto.getEmail());
            userEntity.setPassword(registerAndAccountDto.getPassword());
            UserEntity savedUser = userRepository.save(userEntity);

            AccountEntity accountEntity = new AccountEntity();
            accountEntity.setBalance(registerAndAccountDto.getBalance());
            accountEntity.setFullName(registerAndAccountDto.getFullName());
            accountEntity.setPin(registerAndAccountDto.getPin());

            accountEntity.setUser(savedUser);
            accountRepository.save(accountEntity);

            userEntity.setAccountEntity(accountEntity);

            return new ResponseEntity<>(registerAndAccountDto, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
