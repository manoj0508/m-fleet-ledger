package com.fleet.ledger.app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Request payload for creating a new user")
public class UserRequest {

    @Schema(description = "First name of the user",example = "Manoj", requiredMode = Schema.RequiredMode.REQUIRED)
    private String fName;

    @Schema(description = "Last name of the user",example = "Kumar", requiredMode = Schema.RequiredMode.AUTO)
    private String lName;

    @Schema(description = "Mobile no of the user",example = "9581510004", requiredMode = Schema.RequiredMode.REQUIRED)
    private String mobile;

    @Schema(description = "email id of the user",example = "Manoj.k@gmail.com", requiredMode = Schema.RequiredMode.AUTO)
    private String email;

    @Schema(description = "User type to identify user is customer or driver",example = "CUSTOMER", requiredMode = Schema.RequiredMode.REQUIRED)
    private UserType userType;

    @Override
    public String toString() {
        return "UserRequest{" +
                "fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", userType=" + userType +
                '}';
    }
}
