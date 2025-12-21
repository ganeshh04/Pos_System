package com.gatmane.Service.impl;

import com.gatmane.Service.StoreService;
import com.gatmane.Service.UserService;
import com.gatmane.domain.StoreStatus;
import com.gatmane.exceptions.UserException;
import com.gatmane.mapper.StoreMapper;
import com.gatmane.model.Store;
import com.gatmane.model.StoreContact;
import com.gatmane.model.User;
import com.gatmane.payload.dto.StoreDto;
import com.gatmane.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final UserService userService;
    @Override
    public StoreDto createStore(StoreDto storeDTO, User user) {
        Store store= StoreMapper.toEntity(storeDTO,user);
        Store savedStore = storeRepository.save(store);

        return StoreMapper.toDTO(savedStore);

    }

    @Override
    public StoreDto getStoreById(Long id) throws Exception {

        Store store=storeRepository.findById(id).orElseThrow(
                ()->new Exception("store not found")
        );
        return StoreMapper.toDTO(store);
    }

    @Override
    public List<StoreDto> getAllStores() {

         List<Store>dtos= storeRepository.findAll();
       return  dtos.stream().map(StoreMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public Store getStoreByAdmin() throws UserException {
        User admin=userService.getCurrentUser();
        return storeRepository.findByStoreAdminId(admin.getId());
    }



    @Override
    public StoreDto updateStore(Long id,StoreDto storeDto) throws Exception {
        User currentUser=userService.getCurrentUser();

        Store existing=storeRepository.findByStoreAdminId(currentUser.getId());

        if(existing==null){
            throw new Exception("store not found");
        }
        existing.setBrand(storeDto.getBrand());
        existing.setDescription(storeDto.getDescription());

        if(storeDto.getStoreType()!=null){
            existing.setStoreType(storeDto.getStoreType());

        }

        if(storeDto.getContact()!=null) {
            StoreContact contact = StoreContact.builder()
                    .address(storeDto.getContact().getAddress())
                    .phone(storeDto.getContact().getPhone())
                    .email(storeDto.getContact().getEmail())
                    .build();
            existing.setContact(contact);
        }
            Store updatedStore=storeRepository.save(existing);
            return StoreMapper.toDTO(updatedStore);


    }

    @Override
    public void deleteStore(Long id) throws UserException {

        Store store=getStoreByAdmin();
        storeRepository.delete(store);
    }

    @Override
    public StoreDto getStoreByEmployee() throws UserException {
        User currentUser=userService.getCurrentUser();

        if(currentUser==null){
            throw new UserException("you dont have permission to access");
        }
        return  StoreMapper.toDTO(currentUser.getStore());

    }

    @Override
    public StoreDto moderateStore(Long id, StoreStatus status) throws Exception {
        Store store=storeRepository.findById(id).orElseThrow(
                ()-> new Exception("store not found")
        );
        store.setStatus(status);
        Store updatedStore=storeRepository.save(store);
        return StoreMapper.toDTO(updatedStore);
    }


}
