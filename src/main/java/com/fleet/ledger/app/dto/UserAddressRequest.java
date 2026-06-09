package com.fleet.ledger.app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Request payload for creating a new address for user")
public class UserAddressRequest {

    private String line;

    private String city;

    private String state;

    private String pincode;

    @Override
    public String toString() {
        return "UserAddressRequest{" +
                "line='" + line + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", pincode='" + pincode + '\'' +
                '}';
    }
}
