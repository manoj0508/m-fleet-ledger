package com.fleet.ledger.app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "Request payload for creating a new address for user")
public record UserAddressRequest(
        String line,
        String city,
        String state,
        String pincode,
        Integer userId
) {

}
