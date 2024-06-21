package com.bus.repository;
import com.bus.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BusRepository extends JpaRepository<Bus, Long> {
    Optional<List<Bus>> findAllByIsValidIsFalse();
   List<Bus> findAllByIsValidIsTrue();
}
