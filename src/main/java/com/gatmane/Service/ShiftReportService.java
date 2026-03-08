package com.gatmane.Service;

import com.gatmane.exceptions.UserException;
import com.gatmane.model.ShiftReport;
import com.gatmane.payload.dto.ShiftReportDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface ShiftReportService {
    ShiftReportDTO startShift() throws Exception;
    ShiftReportDTO endShift(Long shiftReportId,LocalDateTime shiftEnd)throws Exception;
    ShiftReportDTO getShiftReportById(Long id) throws Exception;
    List<ShiftReportDTO>getAll();
    List<ShiftReportDTO>getShiftReportByBranchId(Long branchId);
    List<ShiftReportDTO>getShiftReportByCashierId(Long cashierID);
    ShiftReportDTO getCurrentShiftProgress(Long cashierId) throws Exception;
    ShiftReportDTO getShiftByCashierAndDate(Long cashierId,LocalDateTime date)throws Exception;
}
