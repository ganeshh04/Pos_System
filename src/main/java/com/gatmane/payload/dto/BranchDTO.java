package com.gatmane.payload.dto;

import com.gatmane.model.Store;
import com.gatmane.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BranchDTO {

    private Long id;


    private String name;

    private String email;

    private String address;


    private String phone;


    private List<String> workingDays;

    private LocalTime closeTime;
    private LocalTime openTime;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private StoreDto store;

    private Long storeId;

    private UserDto manager;

}
