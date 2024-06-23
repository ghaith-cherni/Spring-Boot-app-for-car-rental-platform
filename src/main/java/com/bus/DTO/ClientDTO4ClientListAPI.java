package com.bus.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
 public class ClientDTO4ClientListAPI {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String city;
    private String gender;
    private ZonedDateTime birthdate;
    private Boolean newsletter;
    private Boolean verified;


}
