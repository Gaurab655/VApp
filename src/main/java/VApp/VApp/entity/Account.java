package VApp.VApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private Long accountNumber;
    @NotNull
    private String fullName;
    @NotNull
    @PositiveOrZero(message = "Balance cannot be negative")
    private Double balance;

    @Max(value = 9999,message = "Pin must be 4 digits long ")
    @Min(value = 1000,message = "pin must be 4 digits long")
    private int pin;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    private static long lastAccountNumber = 655000;

    @PrePersist
    private void assignAccountNumber() {
        this.accountNumber = ++lastAccountNumber;
    }
}
