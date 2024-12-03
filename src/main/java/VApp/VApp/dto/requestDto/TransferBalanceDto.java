package VApp.VApp.dto.requestDto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
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
    private int pin;
}
