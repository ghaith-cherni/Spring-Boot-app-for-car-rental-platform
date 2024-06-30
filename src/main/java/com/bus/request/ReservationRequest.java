package com.bus.request;

import com.bus.entity.Client;
import com.bus.entity.Driver;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequest {

    private Long client_id;
    private Long bus_id ;
    private Long driver_id;
    private String CIN;
    private String busType ;
    private ZonedDateTime dateDepart  ;
    private ZonedDateTime dateRetour   ;
    private String lieuDepart ;
    private String Destination ;
    private String nomCompanion ;
    private int num_seats ;
    private String phone  ;
    private String prenomCompanion ;
    private String tripUtility ;
    private String persontype ;

}
