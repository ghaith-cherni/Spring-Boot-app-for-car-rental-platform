package com.bus.repository;
import com.bus.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BusRepository extends JpaRepository<Bus, Long> {
    public Optional<List<Bus>> findAllByIsValidIsFalse();
    public List<Bus> findAllByIsValidIsTrue();
}
