package com.gatmane.mapper;

import com.gatmane.model.Store;
import com.gatmane.model.User;
import com.gatmane.payload.dto.StoreDto;

public class StoreMapper {
    public static StoreDto toDTO(Store store){
        StoreDto storeDto=new StoreDto();
        storeDto.setId(store.getId());
        storeDto.setBrand(store.getBrand());
        storeDto.setDescription(store.getDescription());
        storeDto.setStoreAdmin(UserMapper.toDTO(store.getStoreAdmin()));
        storeDto.setStoreType(store.getStoreType());
        store.setContact(store.getContact());
        storeDto.setCreatedAt(store.getCreatedAt());
        storeDto.setUpdatedAt(store.getUpdatedAt());
        store.setStatus(store.getStatus());
        return storeDto;
    }

    public static Store toEntity(StoreDto storeDto ,User storeAdmin){
        Store store=new Store();
        storeDto.setId(store.getId());
        storeDto.setBrand(store.getBrand());
        storeDto.setDescription(store.getDescription());
        storeDto.setStoreAdmin(UserMapper.toDTO(store.getStoreAdmin()));
        storeDto.setStoreType(store.getStoreType());
        store.setContact(store.getContact());
        storeDto.setCreatedAt(store.getCreatedAt());
        storeDto.setUpdatedAt(store.getUpdatedAt());

        return store;
    }
}
