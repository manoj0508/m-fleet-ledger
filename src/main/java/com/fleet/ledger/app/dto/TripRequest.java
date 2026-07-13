package com.fleet.ledger.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

@Schema(description = "Request payload for adding new Trip ")
public record TripRequest(
        @NotBlank
        String source,

        @NotBlank
        String destination,

        @NotNull
        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDate journeyDate,

        @NotNull
        @JsonFormat(pattern = "hh:mm a")
        LocalTime journeyStartTime,

        @NotNull
        @JsonFormat(pattern = "hh:mm a")
        LocalTime journeyEndTime,

        @NotNull
        @Min(1)
        Integer distance,

        @NotNull
        Integer vehicleId,

        @NotNull
        Integer driverId,

        @NotNull
        Integer customerId,

        TripType tripType
) {
}
