package VApp.VApp.services;

import VApp.VApp.entity.AccountEntity;
import VApp.VApp.entity.UserEntity;
import VApp.VApp.userRepository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class AccountServices {
    @Autowired
    private AccountRepository accountRepository;

    @PostMapping
    public ResponseEntity<AccountEntity> createAccount(AccountEntity accountEntity){
        try {
            AccountEntity accountEntity1 = new AccountEntity();
            accountEntity1.setFullName(accountEntity.getFullName());
            accountEntity1.setBalance(accountEntity.getBalance());
            accountEntity1.setPin(accountEntity.getPin());
            AccountEntity saveAccounts=accountRepository.save(accountEntity1);
            return new ResponseEntity<>(saveAccounts, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
    public List<AccountEntity> getAccounts(){
        return accountRepository.findAll();
    }
}
