package com.gatmane.mapper;

import com.gatmane.model.Branch;
import com.gatmane.model.Inventory;
import com.gatmane.model.Product;
import com.gatmane.payload.dto.InventoryDTO;

public class InventoryMapper {
    public static InventoryDTO toDTO(Inventory inventory){
        return InventoryDTO.builder()
                .id(inventory.getId())
                .branchId(inventory.getBranch().getId())
                .productID(inventory.getProduct().getId())
                .product(ProductMapper.toDTO(inventory.getProduct()))
                .quantity(inventory.getQuantity())
                .build();
    }
    public static Inventory toEntity(InventoryDTO inventoryDTO, Branch branch, Product product){
        return Inventory.builder()
                .branch(branch)
                .product(product)
                .quantity(inventoryDTO.getQuantity())
                .build();
    }
}
