package com.gatmane.payload.dto;

import com.gatmane.domain.UserRole;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
public class UserDto {

    private Long id;


    private String fullname;


    private String email;

    private String phone;


    private UserRole role;

    private String  password;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private LocalDateTime lastLogin;
}
