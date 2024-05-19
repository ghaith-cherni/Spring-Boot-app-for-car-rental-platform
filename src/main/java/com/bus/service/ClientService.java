package com.bus.service;

import com.bus.entity.Client;
import com.bus.repository.ClientRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Cacheable(value = "client")
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
    public Client saveClient(Client client) {
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        return clientRepository.save(client);
    }
    public Optional<Client> findByUsername(String username) {
        return clientRepository.findByUsername(username);
    }
}
