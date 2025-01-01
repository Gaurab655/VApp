package VApp.VApp.service;

import VApp.VApp.dto.requestDto.AdminTransactionDto;
import VApp.VApp.entity.User;
import VApp.VApp.exception.BankException;
import VApp.VApp.repository.TransactionRepo;
import VApp.VApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final UserRepository userRepository;
    private final TransactionRepo transactionRepo;

    public ResponseEntity<?> transactionDetails() throws BankException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User admin = userRepository.findByEmail(email)
                .orElseThrow(() -> new BankException("Admin not found with this email", HttpStatus.NOT_FOUND));

        if (admin != null) {

            List<AdminTransactionDto> transactions = transactionRepo.findAll()
                    .stream()
                    .map(transaction -> new AdminTransactionDto(transaction))
                    .peek(dto -> dto.setStatus(dto.getStatus().toUpperCase()))
                    .toList();

            return ResponseEntity.ok(transactions);
        }

        throw new BankException("Admin user not found", HttpStatus.NOT_FOUND);
    }}
