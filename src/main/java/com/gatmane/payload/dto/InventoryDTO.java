package com.gatmane.payload.dto;

import com.gatmane.model.Branch;
import com.gatmane.model.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDTO {

    private Long id;


    private BranchDTO branch;
private Long branchId;
private  Long productID;

    private ProductDTO product;


    private Integer quantity;

    private LocalDateTime lastUpdate;

}
