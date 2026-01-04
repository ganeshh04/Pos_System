package com.gatmane.payload.dto;

import com.gatmane.domain.UserRole;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;


    private String fullname;


    private String email;

    private String phone;


    private UserRole role;

    private String  password;

    private Long branchId;
    private Long storeId;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private LocalDateTime lastLogin;
}
