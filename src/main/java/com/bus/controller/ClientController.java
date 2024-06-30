package com.bus.controller;

import com.bus.DTO.ClientDTO4ClientListAPI;
import com.bus.entity.Client;
import com.bus.service.ClientService;
import jakarta.persistence.Cacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/ADMIN/locaBus/clients")
public class ClientController {
    @Autowired
    public ClientService clientService;

//    @GetMapping(value = "/allClients")
//    public ResponseEntity<List<Client>> getAllClients(){
//        List<Client> allClients =clientService.getAllClients();
//        return ResponseEntity.ok().body(allClients);
//    }

        @GetMapping(value = "/allClients")
    public ResponseEntity<List<ClientDTO4ClientListAPI>> getAllClients(){
        List<ClientDTO4ClientListAPI> allClients =clientService.getAllClient();
        return ResponseEntity.ok().body(allClients);
    }

    @GetMapping (value = "/clientById/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<Client> getClientById(Long id){
        Client client =clientService.getClientById(id);
        return ResponseEntity.ok().body(client);
    }

    @PostMapping(value = "/deleteClients/{id}")
    public ResponseEntity<String> deleteClient(Long id){
       //clientService.deleteClient(id);
        return ResponseEntity.ok().body("client supprimé");
    }
}
