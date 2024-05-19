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
@Table(name = "owner")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "category")
    private String  category;
    @Column(name = "profilePicture")
    private String  profilePicture;
    @Column(name = "city")
    private String  city;
    @Column(name = "status")
    private String  status;
    @Column(name = "createdAt")
    private ZonedDateTime createdAt;
    @Column(name = "verified")
    private Boolean verified;
    @Column(name = "authenticationMethod")
    private String  authenticationMethod;
    @Column(name = "birthdate")
    private ZonedDateTime  birthdate;
    @Column(name = "companyName")
    private String  companyName;
    @Column(name = "gender")
    private String  gender;
    @Column(name = "email")
    private String email;
    @Column(name = "newsletter")
    private Boolean newsletter;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "password")
    private String password;
    @Column(name = "username")
    private String username;
    @Enumerated(EnumType.STRING)
    private Role role = Role.OWNER;
    @OneToMany(mappedBy = "owner")
    private List<Bus> buses;
}
