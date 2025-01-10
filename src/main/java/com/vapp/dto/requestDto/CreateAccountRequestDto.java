package com.vapp.dto.requestDto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import com.vapp.enums.RolesEnum;


@Getter
@Setter
public class CreateAccountRequestDto {

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

    @NotNull(message = "insert your role")
    private RolesEnum role;
}
