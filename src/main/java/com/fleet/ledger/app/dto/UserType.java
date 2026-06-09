package com.fleet.ledger.app.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Types of user supported by the application")
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
