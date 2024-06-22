package com.bus.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

//@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "start_date")
    private ZonedDateTime startDate;
    @Column(name = "end_date")
    private ZonedDateTime endDate;
    @Column(name = "total_price")
    private int totalPrice;
    @Column(name = "num_seats")
    private int numSeats;
    @Column(name = "created_at")
    private ZonedDateTime createdAt;
    @Column(name = "status")
    private String status;
    @Column(name = "payment_method")
    private String paymentMethod;
    @Column(name = "payment_status")
    private String paymentStatus;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "driver_id")
    private Driver driver;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", nullable = false)
   // @JsonBackReference
    private Client client;
    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;
    @Column(name = "cin")
    private String CIN;
    @Column(name = "bus_type")
    private String bus_type;
    @Column(name = "departure_place")
    private String departure_place;
    @Column(name = "destination")
    private String destination;
    @Column(name = "companion_firstname")
    private String companion_firstname;
    @Column(name = "companion_lastname")
    private String companion_lastname;
    @Column(name = "companion_phone")
    private String companion_phone;
    @Column(name = "trip_utility")
    private String trip_utility;
    @Column(name = "client_category")
    private String client_category;
    @Column(name = "verified")
    private boolean verified;
}
