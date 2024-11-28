package VApp.VApp.services;

import VApp.VApp.dto.requestDto.DebitCreditDto;
import VApp.VApp.entity.Account;
import VApp.VApp.entity.User;
import VApp.VApp.repository.AccountRepository;
import VApp.VApp.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServices {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<Account> createAccount(Account account){
        try {
            Account account1 = this.modelMapper.map(account, Account.class);
            Account saveAccounts=accountRepository.save(account1);
            return new ResponseEntity<>(saveAccounts, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
    public List<Account> getAccounts(){
        return accountRepository.findAll();
    }

    public ResponseEntity<DebitCreditDto> creditAccount(DebitCreditDto debitCreditDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Optional<User> existingUser = userRepository.findByEmail(email);
        if (existingUser.isPresent()){
           Account userAccount = existingUser.get().getAccount();
           int userPin =userAccount.getPin();
           if (userPin == debitCreditDto.getPin()){
               return  new ResponseEntity<>(HttpStatus.OK);
           }else {
               return  new ResponseEntity<>(HttpStatus.FORBIDDEN);
           }


        } else {
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
