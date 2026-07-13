package com.fleet.ledger.app.dto;

public enum FuelType {
    PETROL("PETROL"),
    DIESEL("DIESEL"),
    CNG("CNG"),
    EV("EV");

    private final String fuelType;

    FuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getFuelType() {
        return fuelType;
    }

}
