package VApp.VApp.services;

import VApp.VApp.dto.DebitCreditDto;
import VApp.VApp.entity.Account;
import VApp.VApp.entity.User;
import VApp.VApp.repository.AccountRepository;
import VApp.VApp.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.OpAnd;
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

    public ResponseEntity<Account> creditAccount(DebitCreditDto debitCreditDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Optional<User> userExists = userRepository.findByEmail(email);
        if (userExists.isPresent()) {
            User existingUser = userExists.get();
            Optional<Account> accountExists = accountRepository.findById(existingUser.getId());
            if (accountExists.isPresent()) {
                Account account = accountExists.get();

                // Check if the PIN matches
                if (account.getPin() != debitCreditDto.getPin()) {
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }

                // Credit operation (add balance)
                double newBalance = account.getBalance() + debitCreditDto.getBalance();

                // Update the account balance
                account.setBalance(newBalance);
                accountRepository.save(account);

                return new ResponseEntity<>(account, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Account not found
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);  // Unauthorized if user not found
    }
}
