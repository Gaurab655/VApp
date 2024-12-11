package VApp.VApp.service;

import VApp.VApp.dto.requestDto.DebitCreditDto;
import VApp.VApp.dto.requestDto.TransferBalanceDto;
import VApp.VApp.entity.Account;
import VApp.VApp.entity.BankAccount;
import VApp.VApp.entity.ServiceCharge;
import VApp.VApp.entity.User;
import VApp.VApp.exception.BankException;
import VApp.VApp.exception.UserNotFoundException;
import VApp.VApp.repository.AccountRepository;
import VApp.VApp.repository.BankAccountRepository;
import VApp.VApp.repository.ServiceChargeRepo;
import VApp.VApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountServices {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final BankAccountRepository bankAccountRepository;
    private final ServiceChargeRepo serviceChargeRepo;

    @Transactional
    public ResponseEntity<String> creditAccount(DebitCreditDto debitCreditDto) throws Exception{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Account userAccount = userRepository.findByEmail(email).orElseThrow(()-> new UserNotFoundException("user not found",HttpStatus.NOT_FOUND)).getAccount();

           if (userAccount.getPin().equals(debitCreditDto.getPin())){
               double updatedBalance = userAccount.getBalance()+debitCreditDto.getBalance();
               userAccount.setBalance(updatedBalance);
               accountRepository.save(userAccount);
               return  new ResponseEntity<>("Your updated balance is: " + updatedBalance, HttpStatus.OK);
           }else {
               throw new BankException("Please enter valid pin",HttpStatus.FORBIDDEN);
           }
        }

    @Transactional(rollbackFor =Exception.class)
    public ResponseEntity<String> debitAccount(DebitCreditDto debitCreditDto) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Account account=userRepository.findByEmail(email)
                .orElseThrow(()->new RuntimeException("User not found with email "+email)).getAccount();
        if (!account.getPin().equals(debitCreditDto.getPin()))  return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        double existingBalance = account.getBalance();
        if (existingBalance<debitCreditDto.getBalance()) throw new BankException("You don't have enough balance",HttpStatus.BAD_REQUEST);

        double updatedBalance = existingBalance-debitCreditDto.getBalance();
        account.setBalance(updatedBalance);
        accountRepository.save(account);
        return new ResponseEntity<>("Balance Updated"+updatedBalance,HttpStatus.OK);
    }

    @Transactional(rollbackFor = BankException.class)
    public ResponseEntity<String> transferAmount(TransferBalanceDto transferBalanceDto) throws BankException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User existingUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new BankException("User not found with email " + email,HttpStatus.NOT_FOUND));

        Account senderAccount = existingUser.getAccount();
        Long receiverAccountNumber = transferBalanceDto.getAccountNumber();

        Account receiverAccount = accountRepository.findByAccountNumber(receiverAccountNumber)
                .orElseThrow(() -> new BankException("Account not found with account number " + receiverAccountNumber,HttpStatus.NOT_FOUND));

        if (senderAccount.equals(receiverAccount)){
            throw new BankException("Enter different account number",HttpStatus.BAD_REQUEST);
        }
        double receiverBalance = receiverAccount.getBalance();
        if (senderAccount.getPin() .equals(transferBalanceDto.getPin()) ) {
            double sendingBalance = transferBalanceDto.getBalance();
            if (senderAccount.getBalance() >= sendingBalance && sendingBalance > 0) {
                ServiceCharge serviceChargeEntity = serviceChargeRepo.findByAmountRange(sendingBalance)
                        .orElseThrow(()->new BankException("Cannot complete the transaction",HttpStatus.INTERNAL_SERVER_ERROR));
                double serviceCharge = serviceChargeEntity.getDiscount();
                if (serviceChargeEntity.getType().equalsIgnoreCase("percent")){
                    serviceCharge = (sendingBalance*receiverBalance)/100;
                }
                double sentBalance = senderAccount.getBalance() - transferBalanceDto.getBalance()-serviceCharge;
                senderAccount.setBalance(sentBalance);

                double receiveBalance = receiverBalance + transferBalanceDto.getBalance();
                receiverAccount.setBalance(receiveBalance);

                BankAccount bankAccount = bankAccountRepository.findById(1)
                        .orElseThrow(()->new BankException("user not found exception",HttpStatus.NOT_FOUND));
                double totalServiceCharge = bankAccount.getBalance()+serviceCharge;
                bankAccount.setBalance(totalServiceCharge);
                bankAccountRepository.save(bankAccount);

                accountRepository.save(senderAccount);
                accountRepository.save(receiverAccount);

                return new ResponseEntity<>("Transfer success with service charge : "+serviceCharge, HttpStatus.OK);
            } else {
                throw new BankException("Insufficient balance ! please enter valid balance",HttpStatus.BAD_REQUEST);
            }
        } else {
            throw new BankException("Pin not valid",HttpStatus.UNAUTHORIZED);
        }
    }



    public ResponseEntity<String> checkBalance() throws Exception{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

            Account account = userRepository.findByEmail(email).get().getAccount();
            double balance  = account.getBalance();
            String message  = "Your total Balance is : "+balance;
            return new ResponseEntity<>(message,HttpStatus.FOUND);
}
}
