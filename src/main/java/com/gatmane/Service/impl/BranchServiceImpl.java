package com.gatmane.Service.impl;

import com.gatmane.Service.BranchService;
import com.gatmane.Service.UserService;
import com.gatmane.exceptions.UserException;
import com.gatmane.mapper.BranchMapper;
import com.gatmane.model.Branch;
import com.gatmane.model.Store;
import com.gatmane.model.User;
import com.gatmane.payload.dto.BranchDTO;
import com.gatmane.repository.BranchRepository;
import com.gatmane.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final StoreRepository storeRepository;
    private final UserService userService;
    @Override
    public BranchDTO createBranch(BranchDTO branchDTO) throws UserException {
        User currentUser=userService.getCurrentUser();
        Store store=storeRepository.findByStoreAdminId(currentUser.getId());

        Branch branch= BranchMapper.toEntity(branchDTO,store);
        Branch savedBranch=branchRepository.save(branch);

        savedBranch.setStore(store); // 🔥 IMPORT
        return BranchMapper.toDTO(savedBranch);


    }

    @Override
    public BranchDTO updateBrnach(Long id, BranchDTO branchDTO) throws Exception {

        Branch existing=branchRepository.findById(id).orElseThrow(
                ()-> new Exception("branch not exist")
        );
        existing.setName(branchDTO.getName());
        existing.setWorkingDays(branchDTO.getWorkingDays());
        existing.setEmail(branchDTO.getEmail());
        existing.setPhone(branchDTO.getPhone());
        existing.setAddress(branchDTO.getAddress());
        existing.setOpenTime(branchDTO.getOpenTime());
        existing.setCloseTime(branchDTO.getCloseTime());
        existing.setUpdatedAt(branchDTO.getUpdatedAt());

        Branch updatedBranch=branchRepository.save(existing);
        return BranchMapper.toDTO(updatedBranch);

    }

    @Override
    public void deleteBrnach(Long id) throws Exception {
        Branch existing=branchRepository.findById(id).orElseThrow(
                ()-> new Exception("branch not exist")
        );
       branchRepository.delete(existing);
    }

    @Override
    public List<BranchDTO> getAllBranchByStoreId(Long storeId) {
        List<Branch>branches= branchRepository.findByStoreId(storeId);
        return branches.stream().map(BranchMapper::toDTO)
                .collect(Collectors.toList());

    }

    @Override
    public BranchDTO getBranchById(Long id) throws Exception {
        Branch existing=branchRepository.findById(id).orElseThrow(
                ()-> new Exception("branch not exist")
        );
        return BranchMapper.toDTO(existing);
    }
}
