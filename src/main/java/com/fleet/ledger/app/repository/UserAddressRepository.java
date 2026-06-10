package com.fleet.ledger.app.repository;

import com.fleet.ledger.app.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAddressRepository extends JpaRepository<UserAddress, Integer> {

}
