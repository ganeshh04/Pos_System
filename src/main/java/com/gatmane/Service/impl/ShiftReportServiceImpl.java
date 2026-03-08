package com.gatmane.Service.impl;

import com.gatmane.Service.ShiftReportService;
import com.gatmane.Service.UserService;
import com.gatmane.domain.PaymentType;
import com.gatmane.mapper.ShiftReportMapper;
import com.gatmane.model.*;
import com.gatmane.payload.dto.ShiftReportDTO;
import com.gatmane.repository.OrderRepository;
import com.gatmane.repository.RefundRepository;
import com.gatmane.repository.ShiftReportRepository;
import com.gatmane.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShiftReportServiceImpl implements ShiftReportService {

    private final ShiftReportRepository shiftReportRepository;
    private final UserService userService;
    private final RefundRepository refundRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Override
    public ShiftReportDTO startShift() throws Exception {
        User currentUser=userService.getCurrentUser();
        LocalDateTime shiftStart=LocalDateTime.now();
        LocalDateTime startOfDay=shiftStart.withHour(0).withMinute(0).withSecond(0);

        LocalDateTime endOfDay=shiftStart.withHour(23).withMinute(59).withSecond(59);

        Optional<ShiftReport>existing=shiftReportRepository.findByCashierAndShiftStartBetween(currentUser,startOfDay,endOfDay);

        if(existing.isPresent()){
            throw  new Exception(" Shift already started for today!");
        }

        Branch branch=currentUser.getBranch();
        ShiftReport shiftReport = ShiftReport.builder()
                .cashier(currentUser)
                .branch(branch)
                .shiftStart(shiftStart)
                .build();


        ShiftReport saveReport=shiftReportRepository.save(shiftReport);
        return ShiftReportMapper.toDTO(saveReport);
    }

    @Override
    public ShiftReportDTO endShift(Long shiftReportId, LocalDateTime shiftEnd) throws Exception {
        User curentUser=userService.getCurrentUser();
        ShiftReport shiftReport=shiftReportRepository
                .findByCashierAndShiftEndIsNullOrderByShiftStartDesc(curentUser)
                .orElseThrow(()-> new Exception("Shift not found"));

        shiftReport.setShiftEnd(shiftEnd);

        List<Refund>refunds=refundRepository.findByCashier_IdAndCreatedAtBetween(
               curentUser.getId(), shiftReport.getShiftStart(),shiftReport.getShiftEnd()
        );

        double totalRefunds=refunds.stream()
                .mapToDouble(refund -> refund.getAmount()!=null ? refund.getAmount():0.0).sum();

        List<Order>orders=orderRepository.findByCashierAndCreatedAtBetween(
                curentUser,shiftReport.getShiftStart(),shiftReport.getShiftEnd()
        );

        double totalSales=orders.stream()
                .mapToDouble(Order::getTotalAmount).sum();

        int totalOrder=orders.size();

        double netSales=totalSales-totalRefunds;

        shiftReport.setTotalRefunds(totalRefunds);
        shiftReport.setTotalSales(totalSales);
        shiftReport.setTotalOrders(totalOrder);
        shiftReport.setNetSale(netSales);
        shiftReport.setRecentOrders(getRecentOrders(orders));
        shiftReport.setTopSellingProducts(getTopSellingProducts(orders));
        shiftReport.setPaymentSummaries(getPaymentSummaries(orders,totalSales));
        shiftReport.setRefunds(refunds);

        ShiftReport saveReport=shiftReportRepository.save(shiftReport);
        return ShiftReportMapper.toDTO(saveReport);
    }


    @Override
    public ShiftReportDTO getShiftReportById(Long id) throws Exception {
        ShiftReport report= shiftReportRepository.findById(id).orElseThrow(
                ()->new Exception("shift report not found with given id"+id)
        );
        return ShiftReportMapper.toDTO(report);
    }

    @Override
    public List<ShiftReportDTO> getAll() {

        List<ShiftReport> reports=shiftReportRepository.findAll();
        return reports.stream().map(
                ShiftReportMapper::toDTO
        ).collect(Collectors.toList());
    }

    @Override
    public List<ShiftReportDTO> getShiftReportByBranchId(Long branchId) {



        List<ShiftReport> reports=shiftReportRepository.findByBranchId(branchId);
        return reports.stream().map(
                ShiftReportMapper::toDTO
        ).collect(Collectors.toList());
    }

    @Override
    public List<ShiftReportDTO> getShiftReportByCashierId(Long cashierId) {


        List<ShiftReport> reports=shiftReportRepository.findByCashierId(cashierId);
        return reports.stream().map(
                ShiftReportMapper::toDTO
        ).collect(Collectors.toList());
    }

    @Override
    public ShiftReportDTO getCurrentShiftProgress(Long cashierId) throws Exception {
        User user=userService.getCurrentUser();
        ShiftReport shiftReport=shiftReportRepository.findByCashierAndShiftEndIsNullOrderByShiftStartDesc(user)
                .orElseThrow(()-> new Exception ("no active shift found cashier"));

        LocalDateTime now=LocalDateTime.now();
        List<Order>orders=orderRepository.findByCashierAndCreatedAtBetween(
                user,shiftReport.getShiftStart(),now
        );
        List<Refund> refunds = refundRepository.findByCashier_IdAndCreatedAtBetween(
                user.getId(),
                shiftReport.getShiftStart(),
                now
        );

        double totalRefunds=refunds.stream()
                .mapToDouble(refund -> refund.getAmount()!=null ? refund.getAmount():0.0).sum();



        double totalSales=orders.stream()
                .mapToDouble(Order::getTotalAmount).sum();

        int totalOrder=orders.size();

        double netSales=totalSales-totalRefunds;

        shiftReport.setTotalRefunds(totalRefunds);
        shiftReport.setTotalSales(totalSales);
        shiftReport.setTotalOrders(totalOrder);
        shiftReport.setNetSale(netSales);
        shiftReport.setRecentOrders(getRecentOrders(orders));
        shiftReport.setTopSellingProducts(getTopSellingProducts(orders));
        shiftReport.setPaymentSummaries(getPaymentSummaries(orders,totalSales));
        shiftReport.setRefunds(refunds);

        ShiftReport savedReport=shiftReportRepository.save(shiftReport);
        return ShiftReportMapper.toDTO(savedReport);
    }

    @Override
    public ShiftReportDTO getShiftByCashierAndDate(Long cashierId, LocalDateTime date) throws Exception {
        User cashier=userRepository.findById(cashierId).orElseThrow(
                ()-> new Exception("cashier not found with given id"+cashierId)
        );

        LocalDateTime start=date.withHour(0).withMinute(0).withSecond(0);
        LocalDateTime end=date.withHour(23).withMinute(59).withSecond(59);

        ShiftReport report=shiftReportRepository.findByCashierAndShiftStartBetween(
                cashier,start,end
                                ).orElseThrow(()-> new Exception("shift report not found for cashier"));
        return ShiftReportMapper.toDTO(report);
    }

    // Helprt methodds
    private List<PaymentSummary> getPaymentSummaries(List<Order> orders, double totalSales) {

        //Cash -order 1(amount=1000),order2(amount=1000) => 2000
        //card order 3 => 300
        //upi order 44(amount=500),order 5(amount=500)=>1000

        //cash =30%
        //card=50%
        //upi=20%



        Map<PaymentType,List<Order>>grouped=orders.stream()
                .collect(Collectors.groupingBy(order->order.getPaymentType()!=null?order.getPaymentType():PaymentType.CASH));
        List<PaymentSummary>summaries=new ArrayList<>();
        for(Map.Entry<PaymentType,List<Order>>entry:grouped.entrySet()){
            double amount=entry.getValue().stream()
                    .mapToDouble(Order::getTotalAmount).sum();
            int transactions=entry.getValue().size();
            double percent = totalSales == 0 ? 0 : (amount / totalSales) * 100;

            PaymentSummary ps=new PaymentSummary();
            ps.setType(entry.getKey());
            ps.setTotalAmount(amount);
            ps.setTransactionCount(transactions);
            ps.setPercentage(percent);
            summaries.add(ps);
        }
        return summaries;

    }

    private List<Product> getTopSellingProducts(List<Order> orders) {
        Map<Product,Integer>productSelesMap=new HashMap<>();
        //p1-5
        //p2-1

        for(Order order:orders){
            for(OrderItem item:order.getItems()){
                Product product=item.getProduct();
                productSelesMap.put(product,productSelesMap.getOrDefault(product,0)
                +item.getQuantity());

            }
        }
       return productSelesMap.entrySet().stream()
               .sorted((a,b)->b.getValue().compareTo(a.getValue()))
               .limit(5)
               .map(Map.Entry::getKey)
               .collect(Collectors.toList());

    }

    private List<Order> getRecentOrders(List<Order> orders) {
        return orders.stream()
                .sorted(Comparator.comparing(Order::getCreatedAt).reversed())
                .limit(5)
                .collect(Collectors.toList());

    }

}
