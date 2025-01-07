package vapp.service;

import org.springframework.http.ResponseEntity;
import vapp.dto.requestDto.DebitCreditDto;
import vapp.dto.requestDto.TransferBalanceDto;
import vapp.exception.BankException;

public interface AccountService {
    ResponseEntity<String> creditAccount(DebitCreditDto debitCreditDto) throws Exception;

    ResponseEntity<String> debitAccount(DebitCreditDto debitCreditDto) throws Exception;

    ResponseEntity<String> transferAmount(TransferBalanceDto transferBalanceDto) throws BankException;

    ResponseEntity<String> checkBalance();


}
