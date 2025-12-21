package com.gatmane.payload.dto;

import com.gatmane.model.Store;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ProductDTO {


    private Long id;


    private String name;

    private String description;

    private Double mrp;

    private String sku;

    private String brand;

    private Double sellingPrice;

    private String image;

//    private Category category;

    private Long categoryId;
    private Long storeId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
