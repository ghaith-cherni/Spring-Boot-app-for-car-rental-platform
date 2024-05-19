package com.bus.service;

import com.bus.entity.Owner;
import com.bus.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Cacheable(value = "owner")
    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    public Owner saveOwner(Owner owner) {
        owner.setPassword(passwordEncoder.encode(owner.getPassword()));
        return ownerRepository.save(owner);
    }

    public Optional<Owner> findByUserName(String username) {
        return ownerRepository.findByUsername(username);
    }
}
