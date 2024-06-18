package com.bus.controller;

import com.bus.entity.Client;
import com.bus.service.ClientService;
import jakarta.persistence.Cacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/ADMIN/locaBus/clients")
public class ClientController {
    @Autowired
    public ClientService clientService;
    @GetMapping(value = "/public/allClients")
    public ResponseEntity<List<Client>> getAllClients(){
        List<Client> allClients =clientService.getAllClients();
        return ResponseEntity.ok().body(allClients);
    }
    @GetMapping (value = "/public/clientById/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<Client> getClientById(Long id){
        Client client =clientService.getClientById(id);
        return ResponseEntity.ok().body(client);
    }


}
