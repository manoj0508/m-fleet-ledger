package com.fleet.ledger.app.controller;

import com.fleet.ledger.app.dto.TripRequest;
import com.fleet.ledger.app.dto.TripResponse;
import com.fleet.ledger.app.service.TripService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/trips")
@RequiredArgsConstructor
@Tag(name = "Trip Management", description = "APIs for managing trips")
public class TripController {

    private final TripService tripService;

    @PostMapping
    @Operation(summary = "Create a new trip")
    public ResponseEntity createTrip(@Valid @RequestBody TripRequest request) {
        tripService.addTrip(request);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all trips")
    public ResponseEntity<List<TripResponse>> getAllTrips() {

        return ResponseEntity.ok(tripService.getAllTrips());
    }

    @GetMapping(params = "tripId")
    @Operation(summary = "Get trip by id")
    public ResponseEntity<String> getTripById(Long tripId) {
        return ResponseEntity.ok("Need to implement");
    }

    @DeleteMapping(params = "tripId")
    @Operation(summary = "Delete trip by id")
    public ResponseEntity<Void> deleteTrip(Long tripId) {
        tripService.deleteTrip(tripId);
        return ResponseEntity.noContent().build();
    }
}
