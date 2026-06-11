package com.fleet.ledger.app.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Response payload for vehicle api")
public record VehicleResponse(
        @Schema(description = "vehicle id for internal purpose ", example = "1")
        Integer Id,
        @Schema(description = "vehicle name ", example = "Swift")
        String name,

        @Schema(description = "vehicle type as in Car, Truck etc", example = "CAR")
        String vehicleType,

        @Schema(description = "Vehicle seating capacity", example = "FOUR")
        SeatingCapacity seatingCapacity,

        @Schema(description = "vehicle category like SUV, SEDAN etc", example = "HATCHBACK")
        BodyType bodyType,

        @Schema(description = "vehicle registration no ", example = "KA01AB1234")
        String vehicleNo) {
}
