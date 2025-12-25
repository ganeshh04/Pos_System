package com.gatmane.controller;

import com.gatmane.Service.BranchService;
import com.gatmane.exceptions.UserException;
import com.gatmane.model.Branch;
import com.gatmane.payload.dto.BranchDTO;
import com.gatmane.payload.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/branches")
@RequiredArgsConstructor
public class BranchController {
    private final BranchService branchService;

    @PostMapping
    public ResponseEntity<BranchDTO>createBranch(@RequestBody BranchDTO branchDTO) throws UserException {
        BranchDTO createdBranch=branchService.createBranch(branchDTO);
        return ResponseEntity.ok(createdBranch);
    }


    @GetMapping("/{id}")
    public ResponseEntity<BranchDTO>getBranchById(@PathVariable Long id) throws Exception {
        BranchDTO createdBranch=branchService.getBranchById(id);
        return ResponseEntity.ok(createdBranch);
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<BranchDTO>> getAllBranchByStoreId(@PathVariable Long storeId) throws Exception {
        List<BranchDTO> createdBranch=branchService.getAllBranchByStoreId(storeId);
        return ResponseEntity.ok(createdBranch);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BranchDTO>updateBranch(@PathVariable Long id,
                                                  @RequestBody BranchDTO branchDTO) throws Exception {
        BranchDTO createdBranch=branchService.updateBrnach(id,branchDTO);
        return ResponseEntity.ok(createdBranch);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse>deleteBranchById(@PathVariable Long id) throws Exception {
       branchService.deleteBrnach(id);
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessage("branch deleted successfully");
        return ResponseEntity.ok(apiResponse);
    }

}
