package com.bus.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bus")
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "num_plate")
    private String numPlate;
    @Column(name = "num_seats")
    private int numSeats;
    @Column(name = "created_at")
    private ZonedDateTime createdAt;
    @Column(name = "bus_type")
    private String busType;
    @Column(name = "bus_condition")
    private String busCondition;
    @Column(name = "price_one_day")
    private int priceOneDay;
    @Column(name = "is_valid")
    private boolean isValid;

}
