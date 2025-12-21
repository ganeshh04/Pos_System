package com.gatmane.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
@Embeddable
public class StoreContact {
    private String address;
    private String phone;


    @Email(message="invalid")
    private String email;


}
