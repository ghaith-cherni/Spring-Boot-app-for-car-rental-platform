package com.bus.controller;

import com.bus.entity.Client;
import com.bus.entity.Reservation;
import com.bus.request.ReservationRequest;
import com.bus.request.ReservationResponse;
import com.bus.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/ADMIN/locaBus/reservations")
public class ReservationsController {
    @Autowired
    public ReservationService reservationService;

    @GetMapping("/allReservations")
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> allReservations = reservationService.getAllReservations();
        return ResponseEntity.ok(allReservations);
    }

    @PostMapping("/addReservations")
    public ResponseEntity<String> makeReservation(@RequestBody ReservationRequest reservation) {
        reservationService.addReservation(reservation);
        return ResponseEntity.ok().body("Reservation made");
    }



//    @GetMapping("/Reservation{Rid}") // get reservation by id for admin
//    public ResponseEntity<Reservation> getReservation4admin(@RequestBody String Rid) {
//        Reservation reservation = ReservationService.getReservation(Rid);
//        return ResponseEntity.ok().body(reservation);
//    }
//    @PutMapping("/Reservation/{Rid}")  // update reservation for admin
//    public ResponseEntity<Reservation> updateReservation4admin(@RequestBody String Rid) {
//        Reservation reservation = ReservationService.getReservation(Rid);
//        return ResponseEntity.ok().body(reservation);
//    }
//    @GetMapping("/Reservation{Rid}") // get reservation by id for client
//    public ResponseEntity<ReservationResponse> getReservation4client(@RequestBody String Rid) {
//        ReservationResponse reservation = ReservationService.getReservation(Rid);
//        return ResponseEntity.ok().body(reservation);
//    }
//
//    @PutMapping("/Reservation/{Rid}") // update reservation for client
//    public ResponseEntity<Reservation> updateReservation4client(@RequestBody String Rid) {
//        Reservation reservation = ReservationService.getReservation(Rid);
//        return ResponseEntity.ok().body(reservation);
//    }
}