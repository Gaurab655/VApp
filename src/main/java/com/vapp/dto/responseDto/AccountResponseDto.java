package com.vapp.dto.responseDto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountResponseDto {
    private Long accountNumber;
    private String fullName;
    private BigDecimal balance;
}
