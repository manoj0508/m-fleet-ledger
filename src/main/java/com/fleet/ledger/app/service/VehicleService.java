package com.fleet.ledger.app.service;

import com.fleet.ledger.app.dto.VehicleRequest;
import com.fleet.ledger.app.dto.VehicleResponse;

import java.util.List;

public interface VehicleService {

    void createNewVehicle(VehicleRequest vehicleRequest);

    List<VehicleResponse> getAllVehicles();

    VehicleResponse getVehicle(Integer vehicleId);

    VehicleResponse getVehicle(String vehicleNo);

    void deleteVehicle(Integer vehicleId);

}
