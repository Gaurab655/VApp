package com.vapp.dto.requestDto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferBalanceRequestDto {
    @NotNull(message = "Enter account number")
    private Long accountNumber;

    @NotNull(message = "Insert balance")
    @Positive(message = "Insert Valid Balance")
    private Double balance;

    @NotNull(message = "Enter pin")
    @Min(value = 1000, message = "pin must be of 4 digit")
    @Max(value = 9999, message = "pin must be of 4 digit")
    private int pin;
}
