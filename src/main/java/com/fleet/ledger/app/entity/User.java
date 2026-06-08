package com.fleet.ledger.app.entity;

import com.fleet.ledger.app.dto.UserType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    private List<UserAddress> userAddress;

    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", emailId='" + emailId + '\'' +
                ", userAddress=" + userAddress +
                '}';
    }
}
