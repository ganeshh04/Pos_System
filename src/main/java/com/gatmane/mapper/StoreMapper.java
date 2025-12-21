package com.gatmane.mapper;

import com.gatmane.model.Store;
import com.gatmane.model.User;
import com.gatmane.payload.dto.StoreDto;

public class StoreMapper {

    // ================= ENTITY → DTO =================
    public static StoreDto toDTO(Store store) {

        if (store == null) return null;

        StoreDto dto = new StoreDto();
        dto.setId(store.getId());
        dto.setBrand(store.getBrand());
        dto.setDescription(store.getDescription());
        dto.setStoreType(store.getStoreType());
        dto.setContact(store.getContact());
        dto.setStatus(store.getStatus());
        dto.setCreatedAt(store.getCreatedAt());
        dto.setUpdatedAt(store.getUpdatedAt());

        if (store.getStoreAdmin() != null) {
            dto.setStoreAdmin(UserMapper.toDTO(store.getStoreAdmin()));
        }

        return dto;
    }

    // ================= DTO → ENTITY =================
    public static Store toEntity(StoreDto dto, User storeAdmin) {

        Store store = new Store();

        store.setBrand(dto.getBrand());
        store.setDescription(dto.getDescription());
        store.setStoreType(dto.getStoreType());
        store.setContact(dto.getContact());
        store.setStatus(dto.getStatus());

        // 🔥 IMPORTANT
        store.setStoreAdmin(storeAdmin);

        return store;
    }
}
