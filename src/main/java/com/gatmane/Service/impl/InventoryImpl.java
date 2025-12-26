package com.gatmane.Service.impl;

import com.gatmane.Service.InventoryService;
import com.gatmane.mapper.InventoryMapper;
import com.gatmane.model.Branch;
import com.gatmane.model.Inventory;
import com.gatmane.model.Product;
import com.gatmane.payload.dto.InventoryDTO;
import com.gatmane.repository.BranchRepository;
import com.gatmane.repository.InventoryRepository;
import com.gatmane.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final BranchRepository branchRepository;
    private final ProductRepository productRepository;
    @Override
    public InventoryDTO createInventory(InventoryDTO inventoryDTO) throws Exception {
        Branch branch=branchRepository.findById(inventoryDTO.getBranchId()).orElseThrow(
                ()-> new Exception("branch not exist")
        );

        Product product=productRepository.findById(inventoryDTO.getProductID()).orElseThrow(
                ()-> new Exception("product is not exist..")
        );

        Inventory inventory= InventoryMapper.toEntity(inventoryDTO,branch,product);
        Inventory savedInventory=inventoryRepository.save(inventory);
        return InventoryMapper.toDTO(savedInventory);
    }

    @Override
    public InventoryDTO updateInventory(Long id,InventoryDTO inventoryDTO) throws Exception {

        Inventory inventory=inventoryRepository.findById(id).orElseThrow(
        ()-> new Exception("inventory not found"));

        inventory.setQuantity(inventoryDTO.getQuantity());
        Inventory updateInventory=inventoryRepository.save(inventory);

        return InventoryMapper.toDTO(updateInventory);
    }

    @Override
    public void deleteInventory(Long id) throws Exception {

        Inventory inventory=inventoryRepository.findById(id).orElseThrow(
                ()-> new Exception("inventory not found"));

        inventoryRepository.delete(inventory);


    }

    @Override
    public InventoryDTO getInventoryById(Long id) throws Exception {
        Inventory inventory=inventoryRepository.findById(id).orElseThrow(
                ()-> new Exception("inventory not found"));


        return InventoryMapper.toDTO(inventory);
    }

    @Override
    public InventoryDTO getInventoryByProductIdAndBranchId(Long productId, Long branchId) {

        Inventory inventory=inventoryRepository.findByProductIdAndBranchId(productId,branchId);

        return InventoryMapper.toDTO(inventory);


    }

    @Override
    public List<InventoryDTO> getAllInventoryByBranchId(Long branchId) {
        List<Inventory>inventories=inventoryRepository.findByBranchId(branchId);


        return inventories.stream().map(
                InventoryMapper::toDTO
        ).collect(Collectors.toList());
    }
}
