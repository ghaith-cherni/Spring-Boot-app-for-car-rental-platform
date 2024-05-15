package com.bus.service;

import com.bus.entity.Client;
import com.bus.repository.ClientRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Cacheable(value = "client")
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

}
