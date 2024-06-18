package com.bus.controller;

import com.bus.entity.Client;
import com.bus.entity.Reservation;
import com.bus.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController("/locaBus/reservations")
public class ReservationsController {
    @Autowired
    public ReservationService reservationService;
    @GetMapping("/locaBus/reservations")
    public ResponseEntity<List<Reservation>> getAllReservations(){
        List<Reservation> allReservations = reservationService.getAllReservations();
        return ResponseEntity.ok(allReservations);
    }
    @PostMapping
    public ResponseEntity<String> makeReservation(@RequestBody Reservation reservation) {
        // Logic to make a reservation
        return ResponseEntity.ok().body("Reservation made");
    }
}
