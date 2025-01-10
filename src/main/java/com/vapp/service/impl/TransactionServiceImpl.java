package com.vapp.service.impl;

import com.vapp.dto.requestDto.TransactionRequestDto;
import com.vapp.repository.TransactionRepository;
import com.vapp.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;

    @Override
    public ResponseEntity<Object> transactionDetails() {
        List<TransactionRequestDto> transactions = transactionRepository.findAll()
                .stream()
                .map(TransactionRequestDto::new)
                .toList();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}
