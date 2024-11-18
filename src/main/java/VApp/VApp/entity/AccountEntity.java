package VApp.VApp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "accounts")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountNumber;
    @NotNull
    private String fullName;
    @NotEmpty
    private Double Balance;
    @NotNull
    @Column(unique = true)
    private String email;
    @NotEmpty
    @Max(value = 9999,message = "Pin must be 4 digits long ")
    @Min(value = 1000,message = "pin must be 4 digits long")
    private int pin;

    @Max(value = 9999, message = "Pin must be 4 digits long ")
    @Min(value = 1000, message = "pin must be 4 digits long")
    public int getPin() {
        return pin;
    }

    public void setPin(@Max(value = 9999, message = "Pin must be 4 digits long ") @Min(value = 1000, message = "pin must be 4 digits long") int pin) {
        this.pin = pin;
    }

    public @NotNull String getEmail() {
        return email;
    }

    public void setEmail(@NotNull String email) {
        this.email = email;
    }

    public Double getBalance() {
        return Balance;
    }

    public void setBalance(Double balance) {
        Balance = balance;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }



}
