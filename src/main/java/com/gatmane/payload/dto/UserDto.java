package com.gatmane.payload.dto;

import com.gatmane.model.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
public class UserDto {

    private int id;


    private String fullname;


    private String email;

    private String phone;


    private UserRole role;

    private String  password;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private LocalDateTime lastLogin;
}
