package com.fleet.ledger.app.repository;

import com.fleet.ledger.app.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

    Optional<Vehicle> findByVehicleNo(String vehicleNo);
}
