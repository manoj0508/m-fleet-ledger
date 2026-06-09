package com.fleet.ledger.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "UserAddress")
@Getter
@Setter
public class UserAddress extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String line;

    private String city;

    private String state;

    private String pincode;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "used_id")
    private User user;

    @Override
    public String toString() {
        return "UserAddress{" +
                "Id=" + Id +
                ", line='" + line + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", pincode='" + pincode + '\'' +
                ", user=" + user +
                '}';
    }
}
