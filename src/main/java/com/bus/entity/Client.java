package com.bus.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;

//@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "created_at")
    private ZonedDateTime createdAt;
    @Column(name = "birthdate")
    private ZonedDateTime birthdate;
    @Column(name = "newsletter")
    private Boolean newsletter;
    @Column(name = "verified")
    private Boolean verified;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "city")
    private String city;
    @Column(name = "gender")
    private String gender;
    @Column(name = "profile_picture")
    private String profilePicture;
    @Column(name = "authentication_method")
    private String authenticationMethod;
    @Column(name = "status")
    private String status;
    @Column(name = "password")
    private String password;
    @Column(name = "username")
    private String username;
    @Column(name = "category")
    private String category;
    @Column(name = "matricule_fiscale")
    private String matricule_fiscale;
    @Enumerated(EnumType.STRING)
    private Role role = Role.CLIENT;
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
  //  @JsonManagedReference
    private List<Reservation> reservations;
}
