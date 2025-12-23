package com.gatmane.repository;

import com.gatmane.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {


    List<Category> findAllByStoreId(Long storeId);
}
