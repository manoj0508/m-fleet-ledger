package com.fleet.ledger.app.entity;

import com.fleet.ledger.app.dto.BodyType;
import com.fleet.ledger.app.dto.FuelType;
import com.fleet.ledger.app.dto.SeatingCapacity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Vehicle")
@Getter
@Setter
public class Vehicle extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String name;

    private String vehicleType;

    private String vehicleCategory;

    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    @Column(name = "seatingCapacity", nullable = false)
    @Enumerated(EnumType.STRING)
    private SeatingCapacity seatingCapacity;

    @Column(name = "bodyType", nullable = false)
    @Enumerated(EnumType.STRING)
    private BodyType bodyType;

    @Column(name = "vehicleNo", nullable = false)
    private String vehicleNo;

    @OneToMany(mappedBy = "vehicle")
    private List<Trip> trip = new ArrayList<>();


}
