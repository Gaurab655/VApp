package VApp.VApp.dto.requestDto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransferBalanceDto {
    @NotNull(message = "Enter account number")
    private Long accountNumber;
    @NotNull(message = "Enter balance")
    @PositiveOrZero(message = "Balance cannot be negative")
    private double balance;
    @NotBlank(message = "Enter pin")
    @Min(value = 1000, message = "pin must be of 4 digit")
    @Max(value = 9999, message = "pin must be of 4 digit")
    private Integer pin;
}
