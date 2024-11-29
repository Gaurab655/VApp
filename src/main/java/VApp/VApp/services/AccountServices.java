package VApp.VApp.services;

import VApp.VApp.dto.requestDto.DebitCreditDto;
import VApp.VApp.dto.requestDto.TransferBalanceDto;
import VApp.VApp.dto.responseDto.BalanceResponse;
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
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional
    public ResponseEntity<String> creditAccount(DebitCreditDto debitCreditDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Optional<User> existingUser = userRepository.findByEmail(email);

        if (existingUser.isPresent()){
            User user = existingUser.get();
           Account userAccount = user.getAccount();

           int userPin =userAccount.getPin();
           if (userPin == debitCreditDto.getPin()){
               double updatedBalance = userAccount.getBalance()+debitCreditDto.getBalance();
               userAccount.setBalance(updatedBalance);
               accountRepository.save(userAccount);
               return  new ResponseEntity<>("Your updated balance is: " + updatedBalance, HttpStatus.OK);
           }else {
               return  new ResponseEntity<>(HttpStatus.FORBIDDEN);
           }
        } else {
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @Transactional
    public ResponseEntity<String> debitAccount(DebitCreditDto debitCreditDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Account account=user.get().getAccount();
        if (account.getPin()!=debitCreditDto.getPin())  return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        double existingBalance = account.getBalance();
        if (existingBalance<debitCreditDto.getBalance()) return  new ResponseEntity<>("You don't have enough balance",HttpStatus.BAD_REQUEST);

        double updatedBalance = existingBalance-debitCreditDto.getBalance();
        account.setBalance(updatedBalance);
        accountRepository.save(account);
        return new ResponseEntity<>("Balance Updated"+updatedBalance,HttpStatus.OK);
    }

@Transactional
public ResponseEntity<String> transferAmount(TransferBalanceDto transferBalanceDto){
Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
String email = authentication.getName();
Optional<User> existingUser = userRepository.findByEmail(email);
if (existingUser.isPresent()){
    Account senderAccount = existingUser.get().getAccount();

    Long receiverAccountNumber=transferBalanceDto.getAccountNumber();
    Optional<Account> receiverAccount = accountRepository.findByAccountNumber(receiverAccountNumber);
    if (receiverAccount.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    double receiverBalance = receiverAccount.get().getBalance();

    if (senderAccount.getPin()== transferBalanceDto.getPin()){
        if (senderAccount.getBalance()>=transferBalanceDto.getBalance() && receiverBalance>0){
            double sentBalance = senderAccount.getBalance()-transferBalanceDto.getBalance();
            senderAccount.setBalance(sentBalance);
            accountRepository.save(senderAccount);

            double receiveBalance = receiverBalance + transferBalanceDto.getBalance();
            receiverAccount.get().setBalance(receiveBalance);
            accountRepository.save(receiverAccount.get());

            return new ResponseEntity<>("Transfer success",HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Enter valid amount",HttpStatus.BAD_REQUEST);
        }
    }else {
        return new ResponseEntity<>("Pin not valid",HttpStatus.UNAUTHORIZED);
    }
}
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
}


public ResponseEntity<String> checkBalance(DebitCreditDto debitCreditDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Optional<User> userExists = userRepository.findByEmail(email);
        if (userExists.isPresent()){
            Account account = userExists.get().getAccount();
            double balance = account.getBalance();
            String message = "Your total Balance is : "+balance;

            return new ResponseEntity<>(message,HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
}
}
