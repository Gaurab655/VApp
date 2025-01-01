package VApp.VApp.dto.requestDto;

import VApp.VApp.enums.Roles;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterAndAccountDto {
    @Column(unique = true)
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String fullName;
    @NotNull
    private BigDecimal balance;
    @NotNull
    @Min(value = 1000,message = "PIN must be at least 1000")
    @Max(value = 9999,message = "PIN must be at most 9999")
    private Integer pin;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Roles role;
}
