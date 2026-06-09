package com.fleet.ledger.app.repository;

import com.fleet.ledger.app.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, Long> {
}
