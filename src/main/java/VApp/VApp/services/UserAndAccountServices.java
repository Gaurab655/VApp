package VApp.VApp.services;

import VApp.VApp.dto.RegisterAndAccountDto;
import VApp.VApp.entity.Account;
import VApp.VApp.entity.User;
import VApp.VApp.repository.AccountRepository;
import VApp.VApp.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
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
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public ResponseEntity<RegisterAndAccountDto> newUserAndAccount(RegisterAndAccountDto registerAndAccountDto){
        try {
            User user = this.modelMapper.map(registerAndAccountDto, User.class);
            User saveUser = userRepository.save(user);

            Account account =this.modelMapper.map(registerAndAccountDto, Account.class);
            account.setUser(saveUser);
            accountRepository.save(account);

            user.setAccountEntity(account);

            return new ResponseEntity<>(registerAndAccountDto, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
