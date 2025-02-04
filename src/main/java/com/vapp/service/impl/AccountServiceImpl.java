package com.vapp.service.impl;

import com.vapp.builder.ServiceResponseBuilder;
import com.vapp.dto.requestDto.DebitCreditRequestDto;
import com.vapp.dto.requestDto.TransferBalanceRequestDto;
import com.vapp.entity.*;
import com.vapp.enums.ServiceChargeTypeEnum;
import com.vapp.exception.BankException;
import com.vapp.model.ApiResponse;
import com.vapp.repository.*;
import com.vapp.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
    public ApiResponse creditAccount(DebitCreditRequestDto debitCreditRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        AccountEntity userAccountEntity = userRepository.findByEmail(email).getAccount();

        if (!userAccountEntity.getPin().equals(debitCreditRequestDto.getPin())) {
            return ServiceResponseBuilder.buildFailedBuilder("Pin not valid");
        }
        BigDecimal updatedBalance = userAccountEntity.getBalance().add(debitCreditRequestDto.getBalance());
        userAccountEntity.setBalance(updatedBalance);
        accountRepository.save(userAccountEntity);
        return ServiceResponseBuilder.buildSuccessBuilder("Your updated balance is: " + updatedBalance);
    }


    @Override
    public ApiResponse debitAccount(DebitCreditRequestDto debitCreditRequestDto) throws BankException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        AccountEntity accountEntity = userRepository.findByEmail(email).getAccount();
        if (!accountEntity.getPin().equals(debitCreditRequestDto.getPin()))
            return ServiceResponseBuilder.buildFailedBuilder("Insert correct pin");

        BigDecimal existingBalance = accountEntity.getBalance();
        if (existingBalance.compareTo(debitCreditRequestDto.getBalance()) < 0)
            return ServiceResponseBuilder.buildFailedBuilder("You don't have enough balance");

        BigDecimal updatedBalance = existingBalance.subtract((debitCreditRequestDto.getBalance()));
        accountEntity.setBalance(updatedBalance);
        accountRepository.save(accountEntity);
        return ServiceResponseBuilder.buildSuccessBuilder("Balance updated : " +updatedBalance);
    }

    @Override
    public ApiResponse transferAmount(TransferBalanceRequestDto transferBalanceRequestDto) throws BankException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        UserEntity existingUserEntity = userRepository.findByEmail(email);
        AccountEntity senderAccount = existingUserEntity.getAccount();
        Long receiverAccountNumber = transferBalanceRequestDto.getAccountNumber();

        AccountEntity receiverAccountEntity = accountRepository.findByAccountNumber(receiverAccountNumber);
        if (senderAccount.equals(receiverAccountEntity)) {
            return ServiceResponseBuilder.buildFailedBuilder("Same account number! Enter different account number");
        }
        if (receiverAccountEntity == null) {
            return ServiceResponseBuilder.buildFailedBuilder("Account number not found");
        }
        if (!senderAccount.getPin().equals(transferBalanceRequestDto.getPin())) {
            return ServiceResponseBuilder.buildFailedBuilder("Pin not matched");
        }

        BigDecimal sendingBalance = transferBalanceRequestDto.getBalance();

        ServiceChargeEntity serviceChargeEntity = serviceChargeRepository.findByAmountRange(sendingBalance)
                .orElseThrow(() -> new BankException("Cannot complete the transaction"));
        BigDecimal serviceCharge = serviceChargeEntity.getCharge();

        if (senderAccount.getBalance().compareTo(sendingBalance.add(serviceCharge)) < 0) {
            return ServiceResponseBuilder.buildFailedBuilder("Insufficient balance in sender account");
        }

        if (serviceChargeEntity.getType() == ServiceChargeTypeEnum.PERCENT) {
            serviceCharge = (sendingBalance.multiply(serviceCharge)).divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);
        }
        TransactionEntity transactionEntity = new TransactionEntity();
            BigDecimal sentBalance = senderAccount.getBalance().subtract(transferBalanceRequestDto.getBalance())
                    .subtract(serviceCharge);
            senderAccount.setBalance(sentBalance);
            BigDecimal receiverBalance = receiverAccountEntity.getBalance();
            BigDecimal receiveBalance = receiverBalance.add(transferBalanceRequestDto.getBalance());
            receiverAccountEntity.setBalance(receiveBalance);

            BankAccountEntity bankAccountEntity = bankAccountRepository.findById(1).orElseThrow(() -> new BankException("System Error"));
            BigDecimal totalServiceCharge = bankAccountEntity.getBalance().add((serviceCharge));
            bankAccountEntity.setBalance(totalServiceCharge);
            bankAccountRepository.save(bankAccountEntity);

            accountRepository.save(senderAccount);
            accountRepository.save(receiverAccountEntity);
            transactionEntity.setStatus("success");

            transactionEntity.setDateTime(LocalDateTime.now());
            transactionEntity.setTransactionType("Transfer");
            transactionEntity.setAmount(transferBalanceRequestDto.getBalance());
            transactionEntity.setServiceCharge(serviceCharge);
            transactionEntity.setTotalAmount(transferBalanceRequestDto.getBalance().add(serviceCharge));
            transactionEntity.setReceiverAccount(receiverAccountEntity);
            transactionEntity.setSenderAccount(senderAccount.getAccountNumber());
            transactionRepository.save(transactionEntity);
        return ServiceResponseBuilder.buildSuccessBuilder("Transfer success with service charge : " + serviceCharge);

    }

    @Override
    public ApiResponse checkBalance() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        AccountEntity accountEntity = userRepository.findByEmail(email).getAccount();
        BigDecimal balance = accountEntity.getBalance();
        return ServiceResponseBuilder.buildSuccessBuilder("Your total Balance is : " + balance.toString());
    }
}
