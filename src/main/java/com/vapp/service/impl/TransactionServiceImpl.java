package com.vapp.service.impl;

import com.vapp.dto.requestDto.TransactionRequestDto;
import com.vapp.entity.BankAccountEntity;
import com.vapp.repository.TransactionRepository;
import com.vapp.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<Object> transactionDetails() {
        List<TransactionRequestDto> transactions = transactionRepository.findAll()
                .stream()
                .map(transactionEntity -> {
                    TransactionRequestDto dto = this.modelMapper.map(transactionEntity, TransactionRequestDto.class);
                    dto.setSendingAccount(transactionEntity.getSenderAccount());
                    if (transactionEntity.getReceiverAccount() != null) {
                        BankAccountEntity receiverAccount = this.modelMapper.map(transactionEntity.getReceiverAccount(), BankAccountEntity.class);
                        dto.setReceiverAccount(receiverAccount);
                    }
                    return dto;
                })
                .toList();
        return ResponseEntity.ok(transactions);
    }
}
