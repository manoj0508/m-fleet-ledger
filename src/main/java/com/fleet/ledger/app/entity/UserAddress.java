package com.fleet.ledger.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "UserAddress",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_user_address_user_id",
                        columnNames = "user_id")
        })
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
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_user_address_user"))
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
