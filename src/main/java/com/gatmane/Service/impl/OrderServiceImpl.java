package com.gatmane.Service.impl;

import com.gatmane.Service.OrderService;
import com.gatmane.Service.UserService;
import com.gatmane.domain.OrderStatus;
import com.gatmane.domain.PaymentType;
import com.gatmane.mapper.OrderMapper;
import com.gatmane.model.*;
import com.gatmane.payload.dto.OrderDto;
import com.gatmane.repository.OrderItemRepository;
import com.gatmane.repository.OrderRepository;
import com.gatmane.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final UserService userService;
    private final ProductRepository productRepository;
    private  final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;


    @Override
    public OrderDto createOrder(OrderDto orderDto) throws Exception {
        User cashier = userService.getCurrentUser();

        Branch branch = cashier.getBranch();
        if (branch == null) {
            throw new Exception("cashier not found");
        }

        if (orderDto.getOrderItems() == null || orderDto.getOrderItems().isEmpty()) {
            throw new Exception("Order items cannot be null or empty");
        }

        Order order = Order.builder()
                .branch(branch)
                .cashier(cashier)
                .customer(orderDto.getCustomer())
                .paymentType(orderDto.getPaymentType())
                .build();

        orderRepository.save(order);

        List<OrderItem> orderItems = orderDto.getOrderItems().stream()
                .map(itemDto -> {
                    Product product = productRepository.findById(itemDto.getProductId())
                            .orElseThrow(() -> new EntityNotFoundException("Product not found"));

                    return OrderItem.builder()
                            .product(product)
                            .quantity(itemDto.getQuantity())
                            .price(product.getSellingPrice() * itemDto.getQuantity())
                            .order(order)
                            .build();
                })
                .collect(Collectors.toList());

        double total = orderItems.stream()
                .mapToDouble(OrderItem::getPrice)
                .sum();

        order.setTotalAmount(total);
        order.setItems(orderItems);

        Order savedOrder = orderRepository.save(order);
        return OrderMapper.toDTO(savedOrder);
    }


    @Override
    public OrderDto getOrderById(Long id) throws Exception {

        return orderRepository.findById(id).
                map(OrderMapper::toDTO).orElseThrow(
                ()-> new Exception("order not found with id"+id)
        );

    }

    @Override
    public List<OrderDto> getOrdersByBranch(Long branchId, Long customerId, Long cashierId, PaymentType paymentType, OrderStatus status) {

        return orderRepository.findByBranchId(branchId).stream().filter(order->customerId==null ||
                (order.getCustomer()!=null  && order.getCustomer().getId().equals(customerId)))
                .filter(order -> cashierId==null ||
                        order.getCashier()!=null &&
                        order.getCashier().getId().equals(cashierId))
                .filter(order -> paymentType==null ||
                        order.getPaymentType()==paymentType)
                .map(OrderMapper::toDTO).collect(Collectors.toList());

    }

    @Override
    public List<OrderDto> getOrderByCashier(Long cashierId) {

        return  orderRepository.findByCashierId(cashierId).stream()
                .map(OrderMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteOrder(Long id) throws Exception {
     Order order=orderRepository.findById(id).orElseThrow(
             ()-> new Exception("Order not found with id"+id)
     );
     orderRepository.delete(order);
    }

    @Override
    public List<OrderDto> getTodayOrderByCustomerBranch(Long branchId) {
        LocalDate today = LocalDate.now();
        LocalDateTime start=today.atStartOfDay();
        LocalDateTime end=today.plusDays(1).atStartOfDay();

        return orderRepository.findByBranchIdAndCreatedAtBetween(branchId,start,end).stream().map(
                OrderMapper::toDTO
        ).collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getOrdersByCustomerId(Long customerId) {
        return orderRepository.findByCustomerId(customerId)
                .stream().map(
                        OrderMapper::toDTO
                ).collect(Collectors.toList());

    }

    @Override
    public List<OrderDto> getTop5RecentOrdersByBranchId(Long branchId) {
        return orderRepository
                .findTop5ByBranchIdOrderByCreatedAtDesc(branchId)
                .stream()
                .map(OrderMapper::toDTO)
                .collect(Collectors.toList());
    }

}
