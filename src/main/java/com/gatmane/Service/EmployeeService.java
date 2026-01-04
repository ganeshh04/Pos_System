package com.gatmane.Service;

import com.gatmane.domain.UserRole;
import com.gatmane.model.User;
import com.gatmane.payload.dto.UserDto;

import java.util.List;

public interface EmployeeService {
    UserDto createStoreEmployee(UserDto employee,Long sotreId) throws Exception;
    UserDto createBranchEmployee(UserDto employee,Long branchId) throws Exception;
    User updateEmployee(Long employee,UserDto employeeDetails) throws Exception;
    void deleteEmployee(Long employeeId) throws Exception;
    List<UserDto> findStoreEmployees(Long storeId, UserRole role) throws Exception;
    List<UserDto> findBranchEmployees(Long branchId, UserRole role) throws Exception;





}
