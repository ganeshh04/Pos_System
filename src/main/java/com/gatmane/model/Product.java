package com.gatmane.model;

import com.gatmane.domain.StoreStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false,unique = true)
    private String sku;
    private Double mrp;

    private String brand;

    private Double sellingPrice;

    private String image;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Store store;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate(){
        createdAt=LocalDateTime.now();

    }


    @PreUpdate
    protected void onUpdate(){
        updatedAt=LocalDateTime.now();
    }


}
