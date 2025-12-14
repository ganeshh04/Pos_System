package com.gatmane.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User {
    @Id
     private int id;

    @Column(nullable = false)
    private String fullname;

    @Column(nullable = false,unique = true)
    @Email(message= "Email should be valid")
    private String email;

    private String phone;

    @Column()
    private UserRole role;

    @Column(nullable = false)
    private  String password;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private LocalDateTime lastLogin;




}
