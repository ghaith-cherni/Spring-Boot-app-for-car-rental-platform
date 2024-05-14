package com.bus.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "startDate")
    private ZonedDateTime startDate;
    @Column(name = "endDate")
    private ZonedDateTime endDate;
    @Column(name = "totalPrice")
    private int totalPrice;
    @Column(name = "numSeats")
    private int numSeats;
    @Column(name = "createdAt")
    private ZonedDateTime createdAt;
    @Column(name = "status")
    private String  status;
    @Column(name = "paymentMethod")
    private String  paymentMethod;
    @Column(name = "paymentStatus")
    private String  paymentStatus;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;

}
