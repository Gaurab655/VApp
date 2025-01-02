package VApp.VApp.dto.requestDto;

import VApp.VApp.enums.Roles;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterAndAccountDto {
    @Column(unique = true)
    @Email(message = "Insert valid email")
    @NotNull(message = "please insert your email")
    private String email;
    @NotNull(message = "please insert your password")
    private String password;
    @NotNull(message = "please Insert your full name")
    private String fullName;

    @PositiveOrZero(message = "Balance cannot be negative")
    private double balance;
    @NotNull(message = "Insert pin")
    @Min(value = 1000, message = "PIN must be a 4-digit number")
    @Max(value = 9999, message = "PIN must be a 4-digit number")
    private Integer pin;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "insert your role")
    private Roles role;
}
