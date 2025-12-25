package com.gatmane.Service;

import com.gatmane.exceptions.UserException;
import com.gatmane.model.User;
import com.gatmane.payload.dto.BranchDTO;

import java.util.List;

public interface BranchService {
    BranchDTO createBranch (BranchDTO branchDTO) throws UserException;
    BranchDTO updateBrnach (Long id,BranchDTO branchDTOr) throws Exception;
    void deleteBrnach (Long id ) throws Exception;
    List<BranchDTO> getAllBranchByStoreId (Long storeId);
    BranchDTO getBranchById(Long id) throws Exception;





}
