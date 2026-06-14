package com.fleet.ledger.app.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TripBill {

    private Long tripId;

    private Long billId;

    private Integer baseCharge;

    private Integer total;


}
