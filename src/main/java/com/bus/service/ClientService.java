package com.bus.service;

import com.bus.DTO.ClientDTO;
import com.bus.DTO.ClientDTO4ClientListAPI;
import com.bus.entity.Client;
import com.bus.repository.ClientRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Cacheable(value = "client")
    public Client saveClient(Client client) {
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        return clientRepository.save(client);
    }

    public Optional<Client> findByUsername(String username) {
        return clientRepository.findByUsername(username);
    }

    public Client getClientById(Long id) {
        return clientRepository.getClientById(id);
    }


    public List<ClientDTO4ClientListAPI> getAllClient() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private ClientDTO4ClientListAPI convertToDto(Client client) {
        return new ClientDTO4ClientListAPI(
                client.getId(),
                client.getFirstName(),
                client.getLastName(),
                client.getEmail(),
                client.getPhoneNumber(),
                client.getCity(),
                client.getGender(),
                client.getBirthdate(),
                client.getNewsletter(),
                client.getVerified()
        );
    }



//    @Cacheable(value = "client")
//    public List<Client> getAllClients() {
//        System.out.println(clientRepository.findAll());
//        return clientRepository.findAll();
//
//    }


}
