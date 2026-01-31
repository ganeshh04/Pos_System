package com.gatmane.repository;

import com.gatmane.model.Refund;
import com.gatmane.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RefundRepository extends JpaRepository<Refund,Long> {
    List<Refund>findByCashierAndCreatedAtBetween(
            Long cahsier ,
            LocalDateTime from,
            LocalDateTime to
    );

    List<Refund>findByCashierId(Long  id);
    List<Refund>findByShiftReportId(Long id);
    List<Refund>findByBranchId(Long id);

}
