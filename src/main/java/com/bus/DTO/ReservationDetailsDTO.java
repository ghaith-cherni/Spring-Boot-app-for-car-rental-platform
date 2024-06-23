package com.bus.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDetailsDTO {
    private ReservationDTO reservation;
    private List<BusDTO> availableBuses;
}