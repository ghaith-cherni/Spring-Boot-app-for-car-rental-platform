package com.bus.service;

import com.bus.entity.Bus;
import com.bus.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

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
}
