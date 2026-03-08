package com.gatmane.payload.dto;

import com.gatmane.domain.PaymentType;
import com.gatmane.model.Customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long id;

    private Long branchId;
    private Long customerId;
    private Double totalAmount;

    private LocalDateTime createdAt;

    private BranchDTO branch;

    private UserDto cashier;

    private Customer customer;

    private PaymentType paymentType;

    private List<OrderItemDto>orderItems= new ArrayList<>();

}
