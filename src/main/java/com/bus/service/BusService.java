package com.bus.service;

import com.bus.entity.Bus;
import com.bus.entity.Client;
import com.bus.entity.Reservation;
import com.bus.repository.BusRepository;
import com.bus.request.ReservationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BusService {
    @Autowired
    public BusRepository busRepository;
    @Cacheable(value = "bus")
    public List<Bus> getAllBuses(){
        return busRepository.findAll();
    }
    public Optional<List<Bus>> getNonValidBuses(){
          Optional<List<Bus>> nonValidBuses = busRepository.findAllByIsValidIsFalse();
        return nonValidBuses ;
    }

    public void addBus(Bus bus) {
        bus.setCreatedAt(ZonedDateTime.now());
        busRepository.save(bus);
    }
}

