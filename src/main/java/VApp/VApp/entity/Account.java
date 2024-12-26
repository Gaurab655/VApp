package VApp.VApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
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

    @Column(unique = true)
    private Long accountNumber;

    @NotNull
    private String fullName;

    @NotNull
    @PositiveOrZero(message = "Balance cannot be negative")
    private Double balance;

    @NotNull
    @Min(value = 1000, message = "PIN must be at least 1000")
    @Max(value = 9999,message = "Pin must be at least 9999")
    private Integer pin;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;



}
