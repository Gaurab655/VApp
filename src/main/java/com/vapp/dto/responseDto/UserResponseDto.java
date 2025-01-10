package com.vapp.dto.responseDto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserResponseDto {
    private int id;
    private String role;
    private String email;
    private AccountResponseDto account;
}
