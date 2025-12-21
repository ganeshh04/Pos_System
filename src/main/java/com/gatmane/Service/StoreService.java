package com.gatmane.Service;

import com.gatmane.model.Store;
import com.gatmane.model.User;
import com.gatmane.payload.dto.StoreDto;

import java.util.List;

public interface StoreService {

    StoreDto createStore(StoreDto storeDTO, User user);
    StoreDto getStoreById(Long id);
    List<StoreDto>getAllStores();
    Store getStoreByAdmin();
    StoreDto updateStore(Long id);
    StoreDto deleteStore(Long id);
    StoreDto getStoreByEmployee();
}
