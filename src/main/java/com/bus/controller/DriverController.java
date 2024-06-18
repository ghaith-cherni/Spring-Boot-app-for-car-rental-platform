package com.bus.controller;

import com.bus.entity.Driver;
import com.bus.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/locaBus/drivers")
public class DriverController {
    @Autowired
    public DriverService driverService;

    @GetMapping(value = "/public/allDrivers")
    public ResponseEntity<List<Driver>> getAllDrivers() {
        List<Driver> allDrivers = driverService.getAllDrivers();
        return ResponseEntity.ok().body(allDrivers);
    }
}
