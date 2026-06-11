package com.fleet.ledger.app.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Response for User address details")
public record UserAddressResponse(
        Integer addressId,
        String line,
        String city,
        String state,
        String pincode
) {

}
