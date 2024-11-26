package VApp.VApp.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class RegisterAndAccountDto {
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String fullName;
    @NotNull
    private double balance;
    @NotNull
    private int pin;
    @NotNull
    private List<String> roles;

    public @NotNull String getEmail() {
        return email;
    }

    public void setEmail(@NotNull String email) {
        this.email = email;
    }

    public @NotNull String getPassword() {
        return password;
    }

    public void setPassword(@NotNull String password) {
        this.password = password;
    }

    @NotNull
    public double getBalance() {
        return balance;
    }

    public void setBalance(@NotNull double balance) {
        this.balance = balance;
    }


    @NotNull
    public int getPin() {
        return pin;
    }

    public void setPin(@NotNull int pin) {
        this.pin = pin;
    }

    public @NotNull String getFullName() {
        return fullName;
    }

    public void setFullName(@NotNull String fullName) {
        this.fullName = fullName;
    }

    public @NotNull List<String> getRoles() {
        return roles;
    }

    public void setRoles(@NotNull List<String> roles) {
        this.roles = roles;
    }
}
