package com.fleet.ledger.app.service;

import com.fleet.ledger.app.dto.TripRequest;
import com.fleet.ledger.app.dto.TripResponse;

import java.util.List;

public interface TripService {

    void addTrip(TripRequest tripRequest);

    List<TripResponse> getAllTrips();

    List<TripResponse> getAllTripsForVehicleNo(String vehicleNo);

    void deleteTrip(Long tripId);
}
