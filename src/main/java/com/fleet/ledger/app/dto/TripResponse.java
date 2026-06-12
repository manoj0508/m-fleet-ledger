package com.fleet.ledger.app.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalTime;

@Schema(description = "Response payload for trip ")
public record TripResponse(
        Long id,
        String tripId,
        String source,

        String destination,

        LocalDate journeyDate,

        LocalTime journeyStartTime,

        LocalTime journeyEndTime,

        Integer distance,

        VehicleResponse vehicle,

        UserResponse driver,

        UserResponse customer
) {
}
