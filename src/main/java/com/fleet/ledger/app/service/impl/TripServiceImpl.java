package com.fleet.ledger.app.service.impl;

import com.fleet.ledger.app.dto.TripRequest;
import com.fleet.ledger.app.dto.TripResponse;
import com.fleet.ledger.app.dto.UserResponse;
import com.fleet.ledger.app.dto.VehicleResponse;
import com.fleet.ledger.app.entity.Trip;
import com.fleet.ledger.app.entity.User;
import com.fleet.ledger.app.entity.Vehicle;
import com.fleet.ledger.app.repository.TripRepository;
import com.fleet.ledger.app.repository.UserRepository;
import com.fleet.ledger.app.repository.VehicleRepository;
import com.fleet.ledger.app.service.TripService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static com.fleet.ledger.app.utils.Utils.isNotNUll;

@Service
public class TripServiceImpl implements TripService {

    private static final Logger logger = LoggerFactory.getLogger(TripServiceImpl.class);


    private final TripRepository tripRepository;
    private final VehicleRepository vehicleRepository;
    private final UserRepository userRepository;


    public TripServiceImpl(TripRepository tripRepository,
                           VehicleRepository vehicleRepository,
                           UserRepository userRepository) {
        this.tripRepository = tripRepository;
        this.vehicleRepository = vehicleRepository;
        this.userRepository = userRepository;
    }


    @Override
    public void addTrip(TripRequest tripRequest) {

        if (isNotNUll(tripRequest)) {
            Trip trip = new Trip();

            trip.setTripId("T-"+ getTripId());
            trip.setDestination(tripRequest.destination());
            trip.setSource(tripRequest.source());
            trip.setJourneyDate(tripRequest.journeyDate());
            trip.setJourneyStartTime(tripRequest.journeyStartTime());
            trip.setJourneyEndTime(tripRequest.journeyEndTime());
            trip.setTripType(tripRequest.tripType());

            User customer = userRepository.getReferenceById(tripRequest.customerId());
            trip.setCustomer(customer);
            User driver = userRepository.getReferenceById(tripRequest.driverId());
            trip.setDriver(driver);

            Vehicle vehicle = vehicleRepository.getReferenceById(tripRequest.vehicleId());
            trip.setVehicle(vehicle);

            trip.setDistance(tripRequest.distance());

            tripRepository.save(trip);
            logger.info("new trip added in db for trip id {}", trip.getTripId());
        }
    }

    @Override
    public List<TripResponse> getAllTrips() {
        return tripRepository.findAll()
                .stream()
                .map(trip -> new TripResponse(
                        trip.getId(),
                        trip.getTripId(),
                        trip.getSource(),
                        trip.getDestination(),
                        trip.getJourneyDate(),
                        trip.getJourneyStartTime(),
                        trip.getJourneyEndTime(),
                        trip.getDistance(),
                        trip.getTripType(),

                        new VehicleResponse(
                                trip.getVehicle().getId(),
                                trip.getVehicle().getName(),
                                trip.getVehicle().getVehicleType(),
                                trip.getVehicle().getSeatingCapacity(),
                                trip.getVehicle().getBodyType(),
                                trip.getVehicle().getVehicleNo()
                        ),

                        createUserResponse(trip.getDriver()),

                        createUserResponse(trip.getCustomer())
                ))
                .toList();
    }

    private UserResponse createUserResponse(User user) {
        UserResponse response = new UserResponse();
        response.setUserId(user.getId());
        response.setFName(user.getFirstName());
        response.setMobileNo(user.getMobileNo());
        return response;
    }

    @Override
    public List<TripResponse> getAllTripsForVehicleNo(String vehicleNo) {
        return List.of();
    }

    @Override
    public void deleteTrip(Long tripId) {
        tripRepository.deleteById(tripId);
        logger.info("trip deleted for id {} ", tripId);
    }

    private String getTripId(){
        LocalDate currentDate = LocalDate.now();
        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
        String date = dayOfWeek.toString() + "-" + currentDate.getMonth().toString() + "-" + LocalDate.now().getYear();
        return generateNumber() +"-"+ date;
    }

    private int generateNumber() {
        return ThreadLocalRandom.current().nextInt(10000);
    }
}
