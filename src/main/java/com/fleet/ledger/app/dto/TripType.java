package com.fleet.ledger.app.dto;

public enum TripType {
    AC("AC"),
    NON_AC("NON-AC");

    private String tripType;

    TripType(String tripType){
        this.tripType = tripType;
    }

    public String getTripType(){
        return tripType;
    }
}
