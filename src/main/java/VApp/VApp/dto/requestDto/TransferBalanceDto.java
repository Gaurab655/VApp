package VApp.VApp.dto.requestDto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class TransferBalanceDto {
    @NotNull
    private Long accountNumber;
    @NotNull
    @PositiveOrZero(message = "Balance cannot be negative")
    private double balance;
    @NotNull
    private int pin;

    public @NotNull Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(@NotNull Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    @NotNull
    public int getPin() {
        return pin;
    }

    public void setPin(@NotNull int pin) {
        this.pin = pin;
    }

    @NotNull
    public double getBalance() {
        return balance;
    }

    public void setBalance(@NotNull double balance) {
        this.balance = balance;
    }
}
