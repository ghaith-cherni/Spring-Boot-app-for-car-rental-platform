package com.bus.controller;

import com.bus.entity.Bus;
import com.bus.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/locaBus/buses")
public class BusController {
    @Autowired
    private BusService busService ;
    @GetMapping (value = "/public/allBuses")
    public ResponseEntity<List<Bus>> getAllBuses(){
        List<Bus> allBuses = busService.getAllBuses();
        return ResponseEntity.ok(allBuses);
    }

}
