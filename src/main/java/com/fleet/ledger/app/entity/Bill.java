package com.fleet.ledger.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table
@Getter
@Setter
public class Bill extends Audit{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billId;

    private Long tripId;

    private LocalDate billDate;

    private Integer tariffCharge;

    private Integer driverCharge;

    private Integer toll;

    private Integer parking;

    private Integer total;

    @Override
    public String toString() {
        return "TripBill{" +
                "billId=" + billId +
                ", tripId=" + tripId +
                ", billDate=" + billDate +
                ", tariffCharge=" + tariffCharge +
                ", driverCharge=" + driverCharge +
                ", toll=" + toll +
                ", parking=" + parking +
                ", total=" + total +
                '}';
    }
}
