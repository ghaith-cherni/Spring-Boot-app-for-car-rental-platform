package com.bus.service;

import com.bus.entity.Reservation;
import com.bus.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
@Autowired
    public ReservationRepository reservationRepository;
    @Cacheable(value = "reservation")
    public List<Reservation> getAllReservations(){
        return reservationRepository.findAll();
    }
}
