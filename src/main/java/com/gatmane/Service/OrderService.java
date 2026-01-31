package com.gatmane.Service;

import com.gatmane.domain.OrderStatus;
import com.gatmane.domain.PaymentType;
import com.gatmane.payload.dto.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto) throws Exception;
    OrderDto getOrderById(Long id) throws Exception;
    List<OrderDto>getOrdersByBranch(Long branchId, Long customerId, Long cashierId, PaymentType paymentType, OrderStatus status);
    List<OrderDto>getOrderByCashier(Long cashierId);
    void deleteOrder(Long id) throws Exception;
    List<OrderDto>getTodayOrderByCustomerBranch(Long branchId);
    List<OrderDto>getOrdersByCustomerId(Long customerId);
    List<OrderDto>getTop5RecentOrdersByBranchId(Long branchId);
}
