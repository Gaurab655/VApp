package com.vapp.dto.responseDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountResponseDto {
    private Long accountNumber;
    private String fullName;
    private double balance;
}
