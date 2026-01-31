package com.gatmane.payload.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gatmane.domain.PaymentType;
import com.gatmane.model.Branch;
import com.gatmane.model.Order;
import com.gatmane.model.ShiftReport;
import com.gatmane.model.User;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder

public class RefundDto {

    private Long id;

    private OrderDto order;
    private Long orderId;

    private String reason;

    private Double amount;


    //private ShiftReport shiftReport;
    private Long shiftReportId;

    private UserDto cashier;
    private String cashierName;

    private BranchDTO branch;
    private Long branchId;

    private PaymentType paymentType;
    private LocalDateTime createdAt;



}
