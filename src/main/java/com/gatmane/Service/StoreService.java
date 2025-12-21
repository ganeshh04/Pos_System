package com.gatmane.Service;

import com.gatmane.domain.StoreStatus;
import com.gatmane.exceptions.UserException;
import com.gatmane.model.Store;
import com.gatmane.model.User;
import com.gatmane.payload.dto.StoreDto;

import java.util.List;

public interface StoreService {

    StoreDto createStore(StoreDto storeDTO, User user);
    StoreDto getStoreById(Long id) throws Exception;
    List<StoreDto>getAllStores();
    Store getStoreByAdmin() throws UserException;
    StoreDto updateStore(Long id,StoreDto storeDto) throws Exception;
    void deleteStore(Long id) throws UserException;
    StoreDto getStoreByEmployee() throws UserException;

    StoreDto moderateStore(Long id, StoreStatus storeStatus)throws Exception;
}
