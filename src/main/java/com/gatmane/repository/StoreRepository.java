package com.gatmane.repository;

import com.gatmane.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store,Long> {

    Store findByStoreAdminId(Long id);

}
