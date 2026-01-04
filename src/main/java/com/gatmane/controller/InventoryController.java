package com.gatmane.controller;

import com.gatmane.Service.InventoryService;
import com.gatmane.payload.dto.InventoryDTO;
import com.gatmane.payload.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventories")
public class InventoryController {
    private final InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<InventoryDTO>create(@RequestBody InventoryDTO inventoryDTO) throws Exception {
        return ResponseEntity.ok(inventoryService.createInventory(inventoryDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryDTO>update(@RequestBody InventoryDTO inventoryDTO,@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(inventoryService.updateInventory(id,inventoryDTO));
    }
    @GetMapping
    public ResponseEntity<InventoryDTO>getInventoryByBrnach(@RequestBody InventoryDTO inventoryDTO) throws Exception {
        return ResponseEntity.ok(inventoryService.createInventory(inventoryDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse>delete( @PathVariable Long id) throws Exception {
         inventoryService.deleteInventory(id);
         ApiResponse apiResponse=new ApiResponse();
         apiResponse.setMessage("Inventory deleted");
         return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/branch/{branchId}/product/{productId}")
    public ResponseEntity<InventoryDTO>getInventoryByProductAndBrnachId(@PathVariable Long branchId,@PathVariable Long productId) throws Exception {
        return ResponseEntity.ok(inventoryService.getInventoryByProductIdAndBranchId(productId,branchId));
    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<InventoryDTO>> getInventoryByBrnach(@PathVariable Long branchId) throws Exception {
        return ResponseEntity.ok(inventoryService.getAllInventoryByBranchId(branchId));
    }
}
