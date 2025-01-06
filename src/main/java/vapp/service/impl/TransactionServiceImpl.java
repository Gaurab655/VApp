package vapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import vapp.dto.requestDto.TransactionDto;
import vapp.entity.UserEntity;
import vapp.exception.BankException;
import vapp.repository.TransactionRepository;
import vapp.repository.UserRepository;
import vapp.service.TransactionService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public ResponseEntity<?> transactionDetails() throws BankException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        UserEntity admin = userRepository.findByEmail(email)
                .orElseThrow(() -> new BankException("Admin not found with this email", HttpStatus.NOT_FOUND));
        if (admin != null) {

            List<TransactionDto> transactions = transactionRepository.findAll()
                    .stream()
                    .map(TransactionDto::new)
                    .peek(dto -> dto.setStatus(dto.getStatus().toUpperCase()))
                    .toList();

            return ResponseEntity.ok(transactions);
        }

        throw new BankException("not found", HttpStatus.NOT_FOUND);
    }
}
