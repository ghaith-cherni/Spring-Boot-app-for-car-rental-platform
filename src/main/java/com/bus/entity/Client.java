package com.bus.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "createdAt")
    private ZonedDateTime createdAt;
    @Column(name = "birthdate")
    private ZonedDateTime  birthdate;
    @Column(name = "newsletter")
    private Boolean newsletter;
    @Column(name = "verified")
    private Boolean verified;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "city")
    private String  city;
    @Column(name = "gender")
    private String  gender;
    @Column(name = "profilePicture")
    private String  profilePicture;
    @Column(name = "authenticationMethod")
    private String  authenticationMethod;
    @Column(name = "status")
    private String  status;
    @Column(name = "password")
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role = Role.CLIENT;
    @OneToMany(mappedBy = "client")
    private List<Reservation> reservations;
}
