package com.fleet.ledger.app.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Trip extends Audit{

    private Long Id;

    private String source;

    private String destination;

    private LocalDate journeyDate;

    private LocalTime journeyStartTime;

    private LocalTime journeyEndTime;

    private Vehicle vehicle;

    private User driver;

    private User customer;
}
