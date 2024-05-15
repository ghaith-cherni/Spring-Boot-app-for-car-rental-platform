package com.bus.service;

import com.bus.entity.Owner;
import com.bus.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;
    @Cacheable(value = "owner")
    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }
}
