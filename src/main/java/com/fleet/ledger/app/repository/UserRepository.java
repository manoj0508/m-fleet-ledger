package com.fleet.ledger.app.repository;

import com.fleet.ledger.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

}
