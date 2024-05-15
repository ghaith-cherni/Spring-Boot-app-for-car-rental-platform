package com.bus.controller;

import com.bus.entity.Owner;
import com.bus.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/locaBus/owners")
public class OwnerController {
    @Autowired
    public OwnerService ownerService;

    @GetMapping(value = "/public/allOwners")
    public ResponseEntity<List<Owner>> getAllOwners() {
        List<Owner> allOwners = ownerService.getAllOwners();
        return ResponseEntity.ok().body(allOwners);
    }
}
