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
    @NotNull
    private Long accountNumber;
    @NotNull
    @PositiveOrZero(message = "Balance cannot be negative")
    private double balance;
    @NotNull
    @Min(value = 1000, message = "PIN must be at least 1000")
    @Max(value = 9999, message = "PIN must be at most 9999")
    private Integer pin;
}
