package com.vapp.dto.requestDto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DebitCreditRequestDto {
    @NotNull(message = "Enter Balance")
    @PositiveOrZero(message = "Balance cannot be negative")
    private Double balance;

    @NotNull(message = "Insert pin")
    @Min(value = 1000, message = "PIN must be 4 digit")
    @Max(value = 9999, message = "PIN must be 4 digit")
    private Integer pin;
}
