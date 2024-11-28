package VApp.VApp.dto.requestDto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class DebitCreditDto {
    @NotNull
    private double balance;
    @NotNull
    @Min(4) @Max(4)
    private int pin;

    @NotNull
    public double getBalance() {
        return balance;
    }

    public void setBalance(@NotNull double balance) {
        this.balance = balance;
    }


    public int getPin() {
        return pin;
    }

    public void setPin(@NotNull @Min(4) @Max(4) int pin) {
        this.pin = pin;
    }
}
