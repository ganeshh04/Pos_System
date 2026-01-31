package com.gatmane.Service;

import com.gatmane.model.Refund;
import com.gatmane.payload.dto.RefundDto;

import java.time.LocalDateTime;
import java.util.List;

public interface RefundService {

    RefundDto createRefund(RefundDto refund)throws Exception;
    List<RefundDto>getAllRefund()throws Exception;
    List<RefundDto> getRefundByCashier(Long cashierId)throws Exception;
    List<RefundDto> getRefundByShiftReport(Long shiftReportId) throws Exception;
    List<RefundDto>getRefundByCashierAndDateRange(Long cashierId,
                                                  LocalDateTime startDate,
                                                  LocalDateTime endDate)throws  Exception;
    List<RefundDto>getRefundByBranch(Long branchID)throws Exception;
    RefundDto getRefundById(Long refundId)throws Exception;
    void deleteRefund(Long refundId)throws  Exception;

}
