package VApp.VApp.services;

import VApp.VApp.entity.AccountEntity;
import VApp.VApp.repository.AccountRepository;
import org.modelmapper.ModelMapper;
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
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<AccountEntity> createAccount(AccountEntity accountEntity){
        try {
            AccountEntity accountEntity1= this.modelMapper.map(accountEntity,AccountEntity.class);
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
