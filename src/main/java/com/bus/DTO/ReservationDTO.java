package com.bus.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {
    private Long id;
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
    private int totalPrice;
    private int numSeats;
    private ZonedDateTime createdAt;
    private String status;
    private String paymentMethod;
    private String paymentStatus;
    private DriverDTO driver;
    private ClientDTO client;
    private BusDTO bus;
    private String CIN;
    private String bus_type;
    private String departure_place;
    private String destination;
    private String companion_firstname;
    private String companion_lastname;
    private String companion_phone;
    private String trip_utility;
    private String client_category;
    private boolean verified;

}
