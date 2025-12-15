package com.gatmane.model;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
