package com.gatmane.repository;

import com.gatmane.model.ShiftReport;
import com.gatmane.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ShiftReportRepository extends JpaRepository<ShiftReport,Long> {

    List<ShiftReport> findByCashierId(Long id);
    List<ShiftReport>findByBranchId(Long id);

    Optional<ShiftReport>findByCashierAndShiftEndIsNullOrderByShiftStartDesc(User cashier);

    Optional<ShiftReport>findByCashierAndShiftStartBetween(User cashier, LocalDateTime start,LocalDateTime end);

}
