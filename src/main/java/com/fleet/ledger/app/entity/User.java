package com.fleet.ledger.app.entity;

import com.fleet.ledger.app.dto.UserType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "User")
@Getter
@Setter
public class User extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String lastName;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "mobileNo", nullable = false)
    private String mobileNo;

    private String emailId;

    @Column(name = "userType", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    private UserAddress userAddress;

    @OneToMany(mappedBy = "driver")
    private List<Trip> driver = new ArrayList<>();

    @OneToMany(mappedBy = "customer")
    private List<Trip> customer = new ArrayList<>();


}
