package com.bus.controller;

import com.bus.entity.Driver;
import com.bus.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/locaBus/owners")
public class DriverController {
    @Autowired
    public DriverService driverService;

    @GetMapping(value = "/public/allOwners")
    public ResponseEntity<List<Driver>> getAllOwners() {
        List<Driver> allOwners = driverService.getAllOwners();
        return ResponseEntity.ok().body(allOwners);
    }
}
