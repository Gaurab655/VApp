package vapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import vapp.dto.requestDto.DebitCreditDto;
import vapp.dto.requestDto.TransferBalanceDto;
import vapp.entity.*;
import vapp.exception.BankException;
import vapp.exception.UserNotFoundException;
import vapp.repository.*;
import vapp.service.AccountService;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final BankAccountRepository bankAccountRepository;
    private final AccountRepository accountRepository;
    private final ServiceChargeRepository serviceChargeRepository;
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<String> creditAccount(DebitCreditDto debitCreditDto) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        AccountEntity userAccountEntity = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("user not found", HttpStatus.NOT_FOUND)).getAccount();

        if (!userAccountEntity.getPin().equals(debitCreditDto.getPin())) {
            return new ResponseEntity<>("Invalid PIN. Please provide the correct PIN to proceed.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        BigDecimal updatedBalance = userAccountEntity.getBalance().add(BigDecimal.valueOf(debitCreditDto.getBalance()));
        userAccountEntity.setBalance(updatedBalance);
        accountRepository.save(userAccountEntity);
        return new ResponseEntity<>("Your updated balance is: " + updatedBalance, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<String> debitAccount(DebitCreditDto debitCreditDto) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        AccountEntity accountEntity = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found with email " + email)).getAccount();
        if (!accountEntity.getPin().equals(debitCreditDto.getPin()))
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);

        BigDecimal existingBalance = accountEntity.getBalance();
        if (existingBalance.compareTo(BigDecimal.valueOf(debitCreditDto.getBalance())) < 0)
            throw new BankException("You don't have enough balance", HttpStatus.BAD_REQUEST);

        BigDecimal updatedBalance = existingBalance.subtract(BigDecimal.valueOf(debitCreditDto.getBalance()));
        accountEntity.setBalance(updatedBalance);
        accountRepository.save(accountEntity);
        return new ResponseEntity<>("Balance Updated : " + updatedBalance, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> transferAmount(TransferBalanceDto transferBalanceDto) throws BankException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        UserEntity existingUserEntity = userRepository.findByEmail(email).orElseThrow(() -> new BankException("User not found with email " + email, HttpStatus.NOT_FOUND));

        AccountEntity senderAccountEntity = existingUserEntity.getAccount();
        Long receiverAccountNumber = transferBalanceDto.getAccountNumber();

        AccountEntity receiverAccountEntity = accountRepository.findByAccountNumber(receiverAccountNumber).orElseThrow(() -> new BankException("Account not found with account number " + receiverAccountNumber, HttpStatus.NOT_FOUND));

        if (senderAccountEntity.equals(receiverAccountEntity)) {
            throw new BankException("same account number! Enter different account number", HttpStatus.BAD_REQUEST);
        }
        BigDecimal receiverBalance = receiverAccountEntity.getBalance();
        if (!senderAccountEntity.getPin().equals(transferBalanceDto.getPin())) {
            return new ResponseEntity<>("Pin not valid", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        double sendingBalance = transferBalanceDto.getBalance();

        ServiceChargeEntity serviceChargeEntity = serviceChargeRepository.findByAmountRange(sendingBalance).orElseThrow(() -> new BankException("Cannot complete the transaction", HttpStatus.INTERNAL_SERVER_ERROR));
        double serviceCharge = serviceChargeEntity.getCharge();

        if (senderAccountEntity.getBalance().compareTo(BigDecimal.valueOf(sendingBalance + serviceCharge)) < 0) {
            return new ResponseEntity<>("Insufficient balance in sender's account.", HttpStatus.BAD_REQUEST);
        }

        if (serviceChargeEntity.getType().equalsIgnoreCase("PERCENT")) {
            serviceCharge = (sendingBalance * serviceCharge) / 100;
        }
        TransactionEntity transactionEntity = new TransactionEntity();
        try {
            BigDecimal sentBalance = senderAccountEntity.getBalance()
                    .subtract(BigDecimal.valueOf(transferBalanceDto.getBalance()))
                    .subtract(BigDecimal.valueOf(serviceCharge));
            senderAccountEntity.setBalance(sentBalance);

            BigDecimal receiveBalance = receiverBalance.add(BigDecimal.valueOf(transferBalanceDto.getBalance()));
            receiverAccountEntity.setBalance(receiveBalance);

            BankAccountEntity bankAccountEntity = bankAccountRepository.findById(1).orElseThrow(() -> new BankException("user not found exception", HttpStatus.NOT_FOUND));
            BigDecimal totalServiceCharge = bankAccountEntity.getBalance().add(BigDecimal.valueOf(serviceCharge));
            bankAccountEntity.setBalance(totalServiceCharge);
            bankAccountRepository.save(bankAccountEntity);

            accountRepository.save(senderAccountEntity);
            accountRepository.save(receiverAccountEntity);
            transactionEntity.setStatus("success");
            return new ResponseEntity<>("Transfer success with service charge : " + serviceCharge, HttpStatus.OK);
        } catch (Exception e) {
            transactionEntity.setStatus("failed");
            System.out.println(e.getMessage());
            return new ResponseEntity<>("no enough balance", HttpStatus.NOT_FOUND);
        } finally {
            transactionEntity.setDateTime(LocalDateTime.now());
            transactionEntity.setTransactionType("transfer");
            transactionEntity.setAmount(transferBalanceDto.getBalance());
            transactionEntity.setServiceCharge(serviceCharge);
            transactionEntity.setTotalAmount(transferBalanceDto.getBalance() + serviceCharge);
            transactionEntity.setBeneficiaryAccountEntity(receiverAccountEntity);
            transactionEntity.setSenderAccount(senderAccountEntity.getAccountNumber());
            transactionRepository.save(transactionEntity);
        }
    }

    @Override
    public ResponseEntity<String> checkBalance() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        AccountEntity accountEntity = userRepository.findByEmail(email).get().getAccount();
        BigDecimal balance = accountEntity.getBalance();
        String message = "Your total Balance is : " + balance.toString();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
