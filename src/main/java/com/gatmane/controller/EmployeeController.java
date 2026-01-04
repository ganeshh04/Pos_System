package com.gatmane.controller;

import com.gatmane.Service.EmployeeService;
import com.gatmane.domain.UserRole;
import com.gatmane.model.User;
import com.gatmane.payload.dto.UserDto;
import com.gatmane.payload.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/store/{storeId}")
    public ResponseEntity<UserDto>createEmployee( @PathVariable Long storeId, @RequestBody UserDto userDto) throws Exception {
        UserDto employee=employeeService.createStoreEmployee(userDto,storeId);
        return ResponseEntity.ok(employee);
    }

    @PostMapping("/branch/{branchId}")
    public ResponseEntity<UserDto>createBranchEmployee( @PathVariable Long branchId, @RequestBody UserDto userDto) throws Exception {
        UserDto employee=employeeService.createBranchEmployee(userDto,branchId);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/{Id}")
    public ResponseEntity<User>updateEmployee( @PathVariable Long id, @RequestBody UserDto userDto) throws Exception {
        User employee=employeeService.updateEmployee(id,userDto);
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<ApiResponse>deleteEmployee(@PathVariable Long id) throws Exception {

        employeeService.deleteEmployee(id);
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessage("Employee deleted");
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/store/{id}")
    public ResponseEntity <List<UserDto>> storeEmployee(@PathVariable Long id, @RequestParam(required = false)UserRole userRole) throws Exception {
      List<UserDto>employee=employeeService.findStoreEmployees(id,userRole);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/branch/{id}")
    public ResponseEntity <List<UserDto>> branchEmployee(@PathVariable Long id, @RequestParam(required = false)UserRole userRole) throws Exception {
        List<UserDto>employee=employeeService.findBranchEmployees(id,userRole);
        return ResponseEntity.ok(employee);
    }


}
