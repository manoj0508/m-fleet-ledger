package com.fleet.ledger.app.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserResponse {

    private Integer userId;
    private String fName;
    private String mobileNo;


    @Override
    public String toString() {
        return "UserResponse{" +
                "userId=" + userId +
                ", fName='" + fName + '\'' +
                ", mobileNo=" + mobileNo +
                '}';
    }
}
