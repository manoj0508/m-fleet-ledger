package com.fleet.ledger.app.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "Trip")
public class Trip extends Audit{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "tripId", nullable = false, unique = true)
    private Long tripId;

    private String source;

    private String destination;

    private LocalDate journeyDate;

    private LocalTime journeyStartTime;

    private LocalTime journeyEndTime;

    @Column(name = "distance", nullable = false)
    private Integer distance;

    @ManyToOne
    @JoinColumn(name = "vehicle_id",nullable = false)
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private User driver;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;
}
