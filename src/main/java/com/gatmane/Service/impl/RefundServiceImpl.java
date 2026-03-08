package com.gatmane.Service.impl;

import com.gatmane.Service.RefundService;
import com.gatmane.Service.UserService;
import com.gatmane.mapper.RefundMapper;
import com.gatmane.model.Branch;
import com.gatmane.model.Order;
import com.gatmane.model.Refund;
import com.gatmane.model.User;
import com.gatmane.payload.dto.RefundDto;
import com.gatmane.repository.OrderRepository;
import com.gatmane.repository.RefundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RefundServiceImpl implements RefundService {

    private  final UserService userService;
    private final OrderRepository orderRepository;
    private final RefundRepository refundRepository;
    @Override
    public RefundDto createRefund(RefundDto refund) throws Exception {
        User cashier=userService.getCurrentUser();

        Order order=orderRepository.findById(refund.getOrderId()).orElseThrow(
                ()-> new Exception("Order not found")
        );

        Branch branch=order.getBranch();

        Refund createRefund=Refund.builder()
                .order(order)
                .cashier(cashier)
                .branch(branch)
                .reason(refund.getReason())
                .amount(refund.getAmount())
                .createdAt(refund.getCreatedAt())
                .build();

        Refund savedRefund=refundRepository.save(createRefund);
        return RefundMapper.toDTO(savedRefund);
    }

    @Override
    public List<RefundDto> getAllRefund() throws Exception {
        return refundRepository.findAll().stream().map(RefundMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<RefundDto> getRefundByCashier(Long cashierId) throws Exception {
        return refundRepository.findByCashierId(cashierId).stream().map(RefundMapper::toDTO).collect(Collectors.toList());

    }

    @Override
    public List<RefundDto> getRefundByShiftReport(Long shiftReportId) throws Exception {
        return refundRepository.findByShiftReportId(shiftReportId).stream().map(RefundMapper::toDTO).collect(Collectors.toList());

    }

    @Override
    public List<RefundDto> getRefundByCashierAndDateRange(Long cashierId, LocalDateTime startDate, LocalDateTime endDate) throws Exception {
        return refundRepository.findByCashier_IdAndCreatedAtBetween(
                cashierId,startDate,endDate
        ).stream().map(RefundMapper::toDTO).collect(Collectors.toList());

    }

    @Override
    public List<RefundDto> getRefundByBranch(Long branchID) throws Exception {
        return refundRepository.findByBranchId(branchID).stream().map(RefundMapper::toDTO).collect(Collectors.toList());

    }

    @Override
    public RefundDto getRefundById(Long refundId) throws Exception {
        return refundRepository.findById(refundId).map(RefundMapper::toDTO).orElseThrow(
                ()-> new Exception("Refund not found")
        );


    }

    @Override
    public void deleteRefund(Long refundId) throws Exception {
       this.getRefundById(refundId);
       refundRepository.deleteById(refundId);
    }
}
