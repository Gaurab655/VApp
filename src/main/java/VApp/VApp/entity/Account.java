package VApp.VApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountNumber;
    @NotNull
    private String fullName;
    @NotNull
    private Double balance;

    @Max(value = 9999,message = "Pin must be 4 digits long ")
    @Min(value = 1000,message = "pin must be 4 digits long")
    private int pin;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;


    public @NotEmpty Double getBalance() {
        return balance;
    }

    @Max(value = 9999, message = "Pin must be 4 digits long ")
    @Min(value = 1000, message = "pin must be 4 digits long")
    public int getPin() {
        return pin;
    }

    public void setPin(@Max(value = 9999, message = "Pin must be 4 digits long ") @Min(value = 1000, message = "pin must be 4 digits long") int pin) {
        this.pin = pin;
    }

    public void setBalance(Double balance) {
       this.balance = balance;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
