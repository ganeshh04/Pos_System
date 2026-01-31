package com.gatmane.controller;

import com.gatmane.Service.OrderService;
import com.gatmane.domain.OrderStatus;
import com.gatmane.domain.PaymentType;
import com.gatmane.payload.dto.OrderDto;
import com.gatmane.payload.dto.OrderItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/orders")
public class OrderController {

    private  final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto>createOrder(@RequestBody OrderDto order) throws Exception {
        return ResponseEntity.ok(orderService.
                createOrder(order));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto>getOrderById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(orderService.
                getOrderById(id));
    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<OrderDto>>getOrderByBranch(@PathVariable Long branchId, @RequestParam(required=false)Long customerId,
                                                    @RequestParam(required=false) Long cashierId,
                                                    @RequestParam(required = false)PaymentType paymentType,
                                                    @RequestParam(required = false)OrderStatus orderStatus) throws Exception {
        return ResponseEntity.ok(orderService.
                getOrdersByBranch(branchId,customerId,cashierId,paymentType,orderStatus));
    }

    @GetMapping("/cashier/{id}")
    public ResponseEntity<List<OrderDto>> getOrderByCashier(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(orderService.
                getOrderByCashier(id));
    }

    @GetMapping("/today/branch/{id}")
    public ResponseEntity<List<OrderDto>> getTodayOrder(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(orderService.
                getTodayOrderByCustomerBranch(id));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<OrderDto>> getCustomersOrder(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(orderService.
                getOrdersByCustomerId(id));
    }

    @GetMapping("/recent/{branchId}")
    public ResponseEntity<List<OrderDto>> getRecentOrder(@PathVariable Long branchId) throws Exception {
        return ResponseEntity.ok(orderService.
                getTop5RecentOrdersByBranchId(branchId));
    }

}
