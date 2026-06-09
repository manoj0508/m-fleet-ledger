package com.fleet.ledger.app.repository;

import com.fleet.ledger.app.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
