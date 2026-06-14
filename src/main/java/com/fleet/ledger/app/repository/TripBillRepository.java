package com.fleet.ledger.app.repository;

import com.fleet.ledger.app.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripBillRepository extends JpaRepository<Bill, Long> {

    public Bill findByTripId(Long tripId);
}
