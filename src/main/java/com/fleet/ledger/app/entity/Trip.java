package com.fleet.ledger.app.entity;

import com.fleet.ledger.app.dto.TripType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Setter
@Getter
@Table(name = "Trip")
public class Trip extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "tripId", nullable = false, unique = true)
    private String tripId;

    private String source;

    private String destination;

    private LocalDate journeyDate;

    private LocalTime journeyStartTime;

    private LocalTime journeyEndTime;

    @Enumerated(EnumType.STRING)
    private TripType tripType = TripType.NON_AC;

    @Column(name = "distance", nullable = false)
    private Integer distance;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private User driver;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    @PrePersist
    void prePersist() {
        if (null == tripType) {
            tripType = TripType.NON_AC;
        }
    }
}
