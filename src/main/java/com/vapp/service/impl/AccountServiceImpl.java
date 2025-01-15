package com.vapp.service.impl;

import com.vapp.dto.requestDto.DebitCreditRequestDto;
import com.vapp.dto.requestDto.TransferBalanceRequestDto;
import com.vapp.entity.*;
import com.vapp.enums.ServiceChargeTypeEnum;
import com.vapp.exception.BankException;
import com.vapp.repository.*;
import com.vapp.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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
    public ResponseEntity<String> creditAccount(DebitCreditRequestDto debitCreditRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        AccountEntity userAccountEntity = userRepository.findByEmail(email).getAccount();

        if (!userAccountEntity.getPin().equals(debitCreditRequestDto.getPin())) {
            return new ResponseEntity<>("Invalid PIN. Please provide the correct PIN to proceed.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        BigDecimal updatedBalance = userAccountEntity.getBalance().add(BigDecimal.valueOf(debitCreditRequestDto.getBalance()));
        userAccountEntity.setBalance(updatedBalance);
        accountRepository.save(userAccountEntity);
        return new ResponseEntity<>("Your updated balance is: " + updatedBalance, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<String> debitAccount(DebitCreditRequestDto debitCreditRequestDto) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        AccountEntity accountEntity = userRepository.findByEmail(email).getAccount();
        if (!accountEntity.getPin().equals(debitCreditRequestDto.getPin()))
            return new ResponseEntity<>("Insert Correct pin", HttpStatus.UNPROCESSABLE_ENTITY);

        BigDecimal existingBalance = accountEntity.getBalance();
        if (existingBalance.compareTo(BigDecimal.valueOf(debitCreditRequestDto.getBalance())) < 0)
            throw new BankException("You don't have enough balance", HttpStatus.BAD_REQUEST);

        BigDecimal updatedBalance = existingBalance.subtract(BigDecimal.valueOf(debitCreditRequestDto.getBalance()));
        accountEntity.setBalance(updatedBalance);
        accountRepository.save(accountEntity);
        return new ResponseEntity<>("Balance Updated : " + updatedBalance, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> transferAmount(TransferBalanceRequestDto transferBalanceRequestDto) throws BankException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        UserEntity existingUserEntity = userRepository.findByEmail(email);
        AccountEntity senderAccount = existingUserEntity.getAccount();
        Long receiverAccountNumber = transferBalanceRequestDto.getAccountNumber();

        AccountEntity receiverAccountEntity = accountRepository.findByAccountNumber(receiverAccountNumber);
        if (senderAccount.equals(receiverAccountEntity)) {
            throw new BankException("same account number! Enter different account number", HttpStatus.BAD_REQUEST);
        }
        BigDecimal receiverBalance = receiverAccountEntity.getBalance();
        if (!senderAccount.getPin().equals(transferBalanceRequestDto.getPin())) {
            return new ResponseEntity<>("Pin not valid", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        double sendingBalance = transferBalanceRequestDto.getBalance();

        ServiceChargeEntity serviceChargeEntity = serviceChargeRepository.findByAmountRange(sendingBalance)
                .orElseThrow(() -> new BankException("Cannot complete the transaction", HttpStatus.INTERNAL_SERVER_ERROR));
        double serviceCharge = serviceChargeEntity.getCharge();

        if (senderAccount.getBalance().compareTo(BigDecimal.valueOf(sendingBalance + serviceCharge)) < 0) {
            return new ResponseEntity<>("Insufficient balance in sender's account.", HttpStatus.BAD_REQUEST);
        }

        if (serviceChargeEntity.getType() == ServiceChargeTypeEnum.PERCENT) {
            serviceCharge = (sendingBalance * serviceCharge) / 100;
        }
        TransactionEntity transactionEntity = new TransactionEntity();
        try {
            BigDecimal sentBalance = senderAccount.getBalance()
                    .subtract(BigDecimal.valueOf(transferBalanceRequestDto.getBalance()))
                    .subtract(BigDecimal.valueOf(serviceCharge));
            senderAccount.setBalance(sentBalance);

            BigDecimal receiveBalance = receiverBalance.add(BigDecimal.valueOf(transferBalanceRequestDto.getBalance()));
            receiverAccountEntity.setBalance(receiveBalance);

            BankAccountEntity bankAccountEntity = bankAccountRepository.findById(1).orElseThrow(() -> new BankException("user not found exception", HttpStatus.NOT_FOUND));
            BigDecimal totalServiceCharge = bankAccountEntity.getBalance().add(BigDecimal.valueOf(serviceCharge));
            bankAccountEntity.setBalance(totalServiceCharge);
            bankAccountRepository.save(bankAccountEntity);

            accountRepository.save(senderAccount);
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
            transactionEntity.setAmount(transferBalanceRequestDto.getBalance());
            transactionEntity.setServiceCharge(serviceCharge);
            transactionEntity.setTotalAmount(BigDecimal.valueOf(transferBalanceRequestDto.getBalance() + serviceCharge));
            transactionEntity.setReceiverAccount(receiverAccountEntity);
            transactionEntity.setSenderAccount(senderAccount.getAccountNumber());
            transactionRepository.save(transactionEntity);
        }
    }

    @Override
    public ResponseEntity<String> checkBalance() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        AccountEntity accountEntity = userRepository.findByEmail(email).getAccount();
        BigDecimal balance = accountEntity.getBalance();
        String message = "Your total Balance is : " + balance.toString();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
