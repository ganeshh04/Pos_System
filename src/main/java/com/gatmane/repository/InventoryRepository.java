package com.gatmane.repository;

import com.gatmane.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface InventoryRepository extends JpaRepository<Inventory,Long> {

    Inventory findByProductIdAndBranchId(Long productId,Long branchId);
    List<Inventory>findByBranchId(Long branchId);
}
