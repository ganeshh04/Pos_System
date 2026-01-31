package com.gatmane.mapper;

import com.gatmane.model.OrderItem;
import com.gatmane.payload.dto.OrderItemDto;

public class OrderItemMapper {

    public static OrderItemDto toDTO(OrderItem item){
        if(item==null) return null;
        return OrderItemDto.builder()
                .id(item.getId())
                .productId(item.getProduct().getId())
                .quantity(item.getQuantity())
                .price(item.getPrice())
                .product(ProductMapper.toDTO(item.getProduct()))
                .build();
    }
}
