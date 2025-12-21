package com.gatmane.mapper;

import com.gatmane.model.Product;
import com.gatmane.model.Store;
import com.gatmane.payload.dto.ProductDTO;

public class ProductMapper {

    public static ProductDTO toDTO(Product product){
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                 .sku(product.getSku())
                .description(product.getDescription())
                .mrp(product.getMrp())
                .sellingPrice(product.getSellingPrice())
                .brand(product.getBrand())
              //  .categoryId(product.ge)
                .storeId(product.getStore()!=null?product.getStore().getId():null)
                .image(product.getImage())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

    public static Product toEntity(ProductDTO productDTO, Store store){
        return Product.builder()
                .name(productDTO.getName())
                //.sk
                .description(productDTO.getDescription())
                .sellingPrice(productDTO.getSellingPrice())
                .brand(productDTO.getBrand())
                .mrp(productDTO.getMrp())
                .build();
    }
}
