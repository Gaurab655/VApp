package VApp.VApp.dto.requestDto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
    private Double balance;
    @NotNull(message = "Insert pin")
    @Positive(message = "Balance can't be negative")
    @Min(value = 1000, message = "PIN must be 4 digit")
    @Max(value = 9999, message = "PIN must be 4 digit")
    private Integer pin;
}
