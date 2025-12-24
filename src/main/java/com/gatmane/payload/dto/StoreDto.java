package com.gatmane.payload.dto;

import com.gatmane.domain.StoreStatus;
import com.gatmane.model.StoreContact;
import com.gatmane.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreDto {
    private Long id;

    @Column(nullable = false)
    private String brand;


    private UserDto storeAdmin;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String description;
    private String storeType;

    private StoreStatus status;


    private StoreContact contact;


}
