package VApp.VApp.service;

import VApp.VApp.dto.requestDto.DebitCreditDto;
import VApp.VApp.dto.requestDto.TransferBalanceDto;
import VApp.VApp.entity.Account;
import VApp.VApp.entity.User;
import VApp.VApp.repository.AccountRepository;
import VApp.VApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

@Service
@RequiredArgsConstructor
public class AccountServices {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

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

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<String> creditAccount(DebitCreditDto debitCreditDto) throws Exception{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User existingUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new Exception("User not found with email" +email));

        Account userAccount = existingUser.getAccount();

           if (userAccount.getPin() == debitCreditDto.getPin()){
               double updatedBalance = userAccount.getBalance()+debitCreditDto.getBalance();
               userAccount.setBalance(updatedBalance);
               accountRepository.save(userAccount);
               return  new ResponseEntity<>("Your updated balance is: " + updatedBalance, HttpStatus.OK);
           }else {
               return  new ResponseEntity<>(HttpStatus.FORBIDDEN);
           }
        }

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<String> debitAccount(DebitCreditDto debitCreditDto) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                    .orElseThrow(()-> new Exception("User  Not found with email" +email));

        Account account=user.getAccount();
        if (account.getPin()!=debitCreditDto.getPin())  return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        double existingBalance = account.getBalance();
        if (existingBalance<debitCreditDto.getBalance()) return  new ResponseEntity<>("You don't have enough balance",HttpStatus.BAD_REQUEST);

        double updatedBalance = existingBalance-debitCreditDto.getBalance();
        account.setBalance(updatedBalance);
        accountRepository.save(account);
        return new ResponseEntity<>("Balance Updated"+updatedBalance,HttpStatus.OK);
    }

@Transactional(rollbackFor = Exception.class)
public ResponseEntity<String> transferAmount(TransferBalanceDto transferBalanceDto) throws Exception{
Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
String email = authentication.getName();
User existingUser = userRepository.findByEmail(email)
                   .orElseThrow(()-> new Exception("User not found with email"+email));
if (existingUser!=null){
    Account senderAccount = existingUser.getAccount();

    Long receiverAccountNumber=transferBalanceDto.getAccountNumber();
    Account receiverAccount = accountRepository.findByAccountNumber(receiverAccountNumber)
            .orElseThrow(()-> new Exception("Account not found with account number"+receiverAccountNumber));
    double receiverBalance = receiverAccount.getBalance();

    if (senderAccount.getPin() == transferBalanceDto.getPin() ){
        if (senderAccount.getBalance() >= transferBalanceDto.getBalance() && transferBalanceDto.getBalance()>0){
            double sentBalance = senderAccount.getBalance()-transferBalanceDto.getBalance();
            senderAccount.setBalance(sentBalance);
            accountRepository.save(senderAccount);

            double receiveBalance = receiverBalance + transferBalanceDto.getBalance();
            receiverAccount.setBalance(receiveBalance);
            accountRepository.save(receiverAccount);

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


public ResponseEntity<String> checkBalance() throws Exception{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User userExists = userRepository.findByEmail(email)
                          .orElseThrow(()-> new Exception("User not exists with email "+email));

            Account account = userExists.getAccount();
            double balance = account.getBalance();
            String message = "Your total Balance is : "+balance;
            return new ResponseEntity<>(message,HttpStatus.FOUND);
}
}
