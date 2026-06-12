package com.fleet.ledger.app.service.impl;

import com.fleet.ledger.app.dto.VehicleRequest;
import com.fleet.ledger.app.dto.VehicleResponse;
import com.fleet.ledger.app.entity.Vehicle;
import com.fleet.ledger.app.repository.VehicleRepository;
import com.fleet.ledger.app.service.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.fleet.ledger.app.utils.Utils.isNotNUll;

@Service
public class VehicleServiceImpl implements VehicleService {

    private static final Logger logger = LoggerFactory.getLogger(VehicleServiceImpl.class);


    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public void createNewVehicle(VehicleRequest vehicleRequest) {
        if (isNotNUll(vehicleRequest)) {
            Vehicle vehicle = new Vehicle();

            vehicle.setVehicleNo(vehicleRequest.vehicleNo());
            vehicle.setVehicleCategory("");
            vehicle.setVehicleType(vehicleRequest.vehicleType());
            vehicle.setName(vehicleRequest.name());
            vehicle.setBodyType(vehicleRequest.bodyType());
            vehicle.setSeatingCapacity(vehicleRequest.seatingCapacity());

            vehicleRepository.save(vehicle);
            logger.info("vehicle detail is saved in db");
        }
    }

    @Override
    public List<VehicleResponse> getAllVehicles() {
        List<VehicleResponse> vehicleResponsesList = new ArrayList<>();
        List<Vehicle> vehicleList = vehicleRepository.findAll();

        vehicleResponsesList = vehicleList.stream().map(vehicle -> {
            return new VehicleResponse(vehicle.getId(), vehicle.getName(), vehicle.getVehicleType(),
                    vehicle.getSeatingCapacity(),
                    vehicle.getBodyType(),
                    vehicle.getVehicleNo());
        }).toList();

        return vehicleResponsesList;
    }

    @Override
    public VehicleResponse getVehicle(Integer vehicleId) {

        VehicleResponse response = null;
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(vehicleId);
        if (vehicleOptional.isPresent()) {
            Vehicle vehicle = vehicleOptional.get();
            response = new VehicleResponse(vehicle.getId(), vehicle.getName(), vehicle.getVehicleType(),
                    vehicle.getSeatingCapacity(),
                    vehicle.getBodyType(),
                    vehicle.getVehicleNo());
        }

        return response;
    }

    @Override
    public VehicleResponse getVehicle(String vehicleNo) {
        VehicleResponse response = null;
        Optional<Vehicle> vehicleOptional = vehicleRepository.findByVehicleNo(vehicleNo);
        if (vehicleOptional.isPresent()) {
            Vehicle vehicle = vehicleOptional.get();
            response = new VehicleResponse(vehicle.getId(), vehicle.getName(), vehicle.getVehicleType(),
                    vehicle.getSeatingCapacity(),
                    vehicle.getBodyType(),
                    vehicle.getVehicleNo());
        }

        return response;
    }

    @Override
    public void deleteVehicle(Integer vehicleId) {
        vehicleRepository.deleteById(vehicleId);
        logger.info("vehicle id {} deleted successfully ", vehicleId);
    }
}
