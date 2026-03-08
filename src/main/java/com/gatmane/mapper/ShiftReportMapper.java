package com.gatmane.mapper;

import com.gatmane.model.Order;
import com.gatmane.model.Product;
import com.gatmane.model.Refund;
import com.gatmane.model.ShiftReport;
import com.gatmane.payload.dto.OrderDto;
import com.gatmane.payload.dto.ProductDTO;
import com.gatmane.payload.dto.RefundDto;
import com.gatmane.payload.dto.ShiftReportDTO;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ShiftReportMapper {

    public static ShiftReportDTO toDTO(ShiftReport entity){
        return ShiftReportDTO.builder()
                .id(entity.getId())
                .shiftEnd(entity.getShiftEnd())
                .shiftStart(entity.getShiftStart())
                .totalSales(entity.getTotalSales())
                .totalOrders(entity.getTotalOrders())
                .netSale(entity.getNetSale())
                .cashier(UserMapper.toDTO(entity.getCashier()))
                .cashierId(entity.getCashier().getId())
                .branchId(entity.getBranch().getId())
                .recentOrders(mapOrders(entity.getRecentOrders()))
                .topSellingProducts(mapProducts(entity.getTopSellingProducts()))
                .refunds(mapRefunds(entity.getRefunds()))
                .paymentSummaries(entity.getPaymentSummaries())
                .build();


    }

    private static List<RefundDto> mapRefunds(List<Refund> refunds) {
        if (refunds == null || refunds.isEmpty()) {
            return Collections.emptyList();
        }
        return refunds.stream()
                .map(RefundMapper::toDTO)
                .collect(Collectors.toList());
    }

    private static List<ProductDTO> mapProducts(List<Product> topSellingProducts) {
        if(topSellingProducts==null || topSellingProducts.isEmpty()){return null;}
        return topSellingProducts.stream().map(ProductMapper::toDTO)
                .collect(Collectors.toList());

    }

    private static List<OrderDto> mapOrders(List<Order> recentOrders) {
        if(recentOrders== null ||recentOrders.isEmpty()){return null;}
        return recentOrders.stream()
                .map(OrderMapper::toDTO).collect(Collectors.toList());
    }


}
