package com.bus.repository;
import com.bus.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public interface BusRepository extends JpaRepository<Bus, Long> {
    Optional<List<Bus>> findAllByIsValidIsFalse();
   List<Bus> findAllByIsValidIsTrue();

    @Query("SELECT b FROM Bus b WHERE b.id NOT IN (SELECT r.bus.id FROM Reservation r WHERE (r.startDate < :endDate AND r.endDate > :startDate))")
    List<Bus> findAvailableBuses(@Param("startDate") ZonedDateTime startDate, @Param("endDate") ZonedDateTime endDate);
}
