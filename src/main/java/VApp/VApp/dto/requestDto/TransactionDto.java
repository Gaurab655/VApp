package VApp.VApp.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {

    private int id;
    private LocalDateTime dateTime;
    private String transactionType;
    private Long beneficiaryAccountId;
    private String beneficiaryAccountName;
    private double amount;
    private double serviceCharge;
    private double totalAmount;
    private String status;
}
