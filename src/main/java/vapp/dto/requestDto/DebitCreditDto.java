package vapp.dto.requestDto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DebitCreditDto {
    @NotNull(message = "Enter Balance")
    @PositiveOrZero(message = "Balance cannot be negative")
    private Double balance;

    @NotNull(message = "Insert pin")
    @Min(value = 1000, message = "PIN must be 4 digit")
    @Max(value = 9999, message = "PIN must be 4 digit")
    private Integer pin;
}
