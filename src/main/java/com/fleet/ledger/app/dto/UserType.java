package com.fleet.ledger.app.dto;

public enum UserType {
    OWNER("owner"), DRIVER("Driver"), CUSTOMER("Client"), ADMIN("admin");

    private String userType;

    UserType(String userType) {
        this.userType = userType;
    }

    public String getUserType() {
        return userType;
    }
}
