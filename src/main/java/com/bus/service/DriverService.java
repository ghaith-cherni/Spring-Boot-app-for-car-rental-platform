package com.bus.service;

import com.bus.entity.Driver;
import com.bus.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DriverService {
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Cacheable(value = "driver")
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    public Driver saveDriver(Driver driver) {
        driver.setPassword(passwordEncoder.encode(driver.getPassword()));
        return driverRepository.save(driver);
    }

    public Optional<Driver> findByUserName(String username) {
        return driverRepository.findByUsername(username);
    }
}
