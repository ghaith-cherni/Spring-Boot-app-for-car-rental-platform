package com.bus.service;

import com.bus.entity.Bus;
import com.bus.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusService {
    @Autowired
    public BusRepository busRepository;
    @Cacheable(value = "bus")
    public List<Bus> getAllBuses(){
        return busRepository.findAll();
    }
}
