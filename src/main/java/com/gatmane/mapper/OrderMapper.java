package com.gatmane.mapper;

import com.gatmane.model.Order;
import com.gatmane.payload.dto.OrderDto;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {
    public static OrderDto toDTO(Order order){
        return OrderDto.builder()
                .id(order.getId())
                .totalAmount(order.getTotalAmount())
                .cashier(UserMapper.toDTO(order.getCashier()))
                .branchId(order.getBranch().getId())
                .customer(order.getCustomer())
                .paymentType(order.getPaymentType())
                .createdAt(order.getCreatedAt())
                .orderItems(order.getItems() == null ?
                        List.of() :
                        order.getItems().stream()
                                .map(OrderItemMapper::toDTO)
                                .collect(Collectors.toList()))

                .build();
    }
}
