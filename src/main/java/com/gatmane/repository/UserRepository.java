package com.gatmane.repository;

import com.gatmane.model.Store;
import com.gatmane.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String Email);
    List<User>findByStore(Store store);
    List<User>findByBranchId(Long branchId);

}
