package com.gatmane.payload.dto;

import com.gatmane.model.Branch;
import com.gatmane.model.Product;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class InventoryDTO {

    private Long id;


    private BranchDTO branch;
private Long branchId;
private  Long productID;

    private ProductDTO product;


    private Integer quantity;

    private LocalDateTime lastUpdate;

}
