package com.bus.controller;

import com.bus.entity.Reservation;
import com.bus.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/locaBus/buses")
public class ReservationsController {
    @Autowired
    public ReservationService reservationService;
    @GetMapping("/locaBus/reservations")
    public ResponseEntity<List<Reservation>> getAllReservations(){
        List<Reservation> allReservations = reservationService.getAllReservations();
        return ResponseEntity.ok(allReservations);
    }
    @PostMapping
    public ResponseEntity<String> makeReservation() {
        // Logic to make a reservation
        return ResponseEntity.ok().body("Reservation made");
    }
}
