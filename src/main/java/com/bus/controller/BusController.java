package com.bus.controller;

import com.bus.entity.Bus;
import com.bus.request.ReservationRequest;
import com.bus.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/ADMIN/locaBus/buses")
public class BusController {
    @Autowired
    private BusService busService ;
    @GetMapping (value = "/allBuses")
    public ResponseEntity<List<Bus>> getAllBuses(){
        List<Bus> allBuses = busService.getAllBuses();
        return ResponseEntity.ok(allBuses);
    }
    @GetMapping (value = "/nonValidBuses")
    public ResponseEntity<Optional<List<Bus>>> getNonValidBuses(){
        Optional<List<Bus>> nonValidBuses = busService.getNonValidBuses();
        return ResponseEntity.ok(nonValidBuses);
    }

    @PostMapping("/addBus")
    public ResponseEntity<String> addBus(@RequestBody Bus bus) {
        System.out.println("Received reservation request: " + bus);
        busService.addBus(bus);
        return ResponseEntity.ok().body("Bus added successfully");
    }
}
