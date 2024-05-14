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
    @Column(name = "numPlate")
    private String numPlate;
    @Column(name = "numSeats")
    private int numSeats;
    @Column(name = "createdAt")
    private ZonedDateTime  createdAt;
    @Column(name = "busType")
    private String  busType;
    @Column(name = "busCondition")
    private String  busCondition;
    @Column(name = "priceOneDay")
    private int  priceOneDay;
    @ManyToOne
    @JoinColumn(name = "fk_owner_id")
    private Owner owner;
}
