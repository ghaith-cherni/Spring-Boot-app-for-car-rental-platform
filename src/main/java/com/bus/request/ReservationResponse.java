package com.bus.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResponse {

    private String client_id;
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
