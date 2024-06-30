package com.bus.controller;

import com.bus.DTO.ReservationDTO;
import com.bus.DTO.ReservationDetailsDTO;
import com.bus.DTO.ReservationUpdateDTO;
import com.bus.entity.Client;
import com.bus.entity.Reservation;
import com.bus.request.ReservationRequest;
import com.bus.request.ReservationResponse;
import com.bus.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/ADMIN/locaBus/reservations")
public class ReservationsController {
    @Autowired
    public ReservationService reservationService;

    @GetMapping("/allReservations")
    public ResponseEntity<List<ReservationDTO>> getAllReservations() {
        List<ReservationDTO> reservations = reservationService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

    @PostMapping("/addReservations")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<String> makeReservation(@RequestBody ReservationRequest reservation) {
        reservationService.addReservation(reservation);
        return ResponseEntity.ok().body("Reservation made");
    }

    @GetMapping("/Reservation/{id}")        //get reservation by reservation id
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable Long id) {
        ReservationDTO reservation = reservationService.getReservationById(id);
        return ResponseEntity.ok(reservation);
    }

    @GetMapping("/ReservationsByClientId/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<ReservationDTO>> getReservationsByClientId(@PathVariable Long id) {
        List<ReservationDTO> reservations = reservationService.getReservationsByClientId(id);
        return ResponseEntity.ok(reservations);
    }


    @PutMapping("/updateReservation")      // for Admin
    public ResponseEntity<ReservationDTO> updateReservation(@RequestBody ReservationUpdateDTO reservationUpdateDTO) {
        ReservationDTO updatedReservation = reservationService.updateReservation(reservationUpdateDTO);
        return ResponseEntity.ok(updatedReservation);
    }

    @GetMapping("/reservationsByDriver/{id}")
    public ResponseEntity<List<ReservationDTO>> getReservationsByDriverId(@PathVariable Long id) {
        List<ReservationDTO> reservations = reservationService.getReservationsByDriverId(id);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/availableBusesByReservationId/{reservationId}")
    public ResponseEntity<ReservationDetailsDTO> getReservationDetailsWithAvailableBuses(@PathVariable Long reservationId) {
        ReservationDetailsDTO reservationDetails = reservationService.getReservationDetailsWithAvailableBuses(reservationId);
        return ResponseEntity.ok(reservationDetails);
    }

//    @GetMapping("/Reservation/{Rid}") // get reservation by id for admin
//    public ResponseEntity<Reservation> getReservation4admin(@RequestBody String Rid) {
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