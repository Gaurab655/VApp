package VApp.VApp.dto;

import jakarta.validation.constraints.NotNull;

public class AccountManagerDto {
    @NotNull
    private double balance;
    @NotNull
    private int pin;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }
}
