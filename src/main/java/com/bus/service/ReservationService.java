package com.bus.service;

import com.bus.entity.Bus;
import com.bus.entity.Client;
import com.bus.entity.Driver;
import com.bus.entity.Reservation;
import com.bus.repository.BusRepository;
import com.bus.repository.ClientRepository;
import com.bus.repository.DriverRepository;
import com.bus.repository.ReservationRepository;
import com.bus.request.ReservationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class ReservationService {
    @Autowired
    public ClientRepository clientRepository;
    @Autowired
    private ReservationRepository reservationRepository;
//    @Autowired
//    public DriverRepository driverRepository;
//    @Autowired
//    private BusRepository busRepository ;
    @Cacheable(value = "reservation")
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }


    public void addReservation(ReservationRequest reservationRequest) {

        Client client = clientRepository.findById(reservationRequest.getClient_id())
                .orElseThrow(() -> new RuntimeException("Client not found"));
/*
        Bus bus = null;
        if (reservationRequest.getBus_id() != null) {
            bus = busRepository.findById(reservationRequest.getBus_id())
                    .orElseThrow(() -> new RuntimeException("Bus not found"));
        }

        Driver driver = driverRepository.findById(reservationRequest.getDriver_id())
                .orElseThrow(() -> new RuntimeException("Client not found"));
*/
        Reservation reservation = new Reservation();
        reservation.setCIN(reservationRequest.getCIN());
        reservation.setStartDate(reservationRequest.getDateDepart());
        reservation.setEndDate(reservationRequest.getDateRetour());
        reservation.setCreatedAt(ZonedDateTime.now());
        reservation.setStatus("waiting");
        reservation.setClient(client);
        reservation.setBus_type(reservationRequest.getBusType());
        reservation.setDeparture_place(reservationRequest.getLieuDepart());
        reservation.setDestination(reservationRequest.getDestination());
        reservation.setCompanion_firstname(reservationRequest.getNomCompanion());
        reservation.setCompanion_lastname(reservationRequest.getPrenomCompanion());
        reservation.setCompanion_phone(reservationRequest.getPhone());
        reservation.setTrip_utility(reservationRequest.getTripUtility());
        reservation.setClient_category(reservationRequest.getPersontype());
        reservation.setNumSeats(reservationRequest.getNum_seats());
        reservation.setVerified(false);
//        reservation.setBus(bus);
//        reservation.setDriver(driver);

        System.out.println("Received reservation request: " + reservation);
        reservationRepository.save(reservation);
    }
}
