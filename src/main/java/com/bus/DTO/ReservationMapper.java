package com.bus.DTO;

import com.bus.entity.Bus;
import com.bus.entity.Client;
import com.bus.entity.Driver;
import com.bus.entity.Reservation;

public class ReservationMapper {

    public static ReservationDTO toDto (Reservation reservation) {
        if (reservation == null) {
            return null;
        }

        ReservationDTO dto = new ReservationDTO();
        dto.setId(reservation.getId());
        dto.setStartDate(reservation.getStartDate());
        dto.setEndDate(reservation.getEndDate());
        dto.setTotalPrice(reservation.getTotalPrice());
        dto.setNumSeats(reservation.getNumSeats());
        dto.setCreatedAt(reservation.getCreatedAt());
        dto.setStatus(reservation.getStatus());
        dto.setPaymentMethod(reservation.getPaymentMethod());
        dto.setPaymentStatus(reservation.getPaymentStatus());
        dto.setCIN(reservation.getCIN());
        dto.setBus_type(reservation.getBus_type());
        dto.setDeparture_place(reservation.getDeparture_place());
        dto.setDestination(reservation.getDestination());
        dto.setCompanion_firstname(reservation.getCompanion_firstname());
        dto.setCompanion_lastname(reservation.getCompanion_lastname());
        dto.setCompanion_phone(reservation.getCompanion_phone());
        dto.setTrip_utility(reservation.getTrip_utility());
        dto.setClient_category(reservation.getClient_category());
        dto.setVerified(reservation.isVerified());

        // Map nested entities
        dto.setDriver(toDriverDto(reservation.getDriver()));
        dto.setClient(toClientDto(reservation.getClient()));
        dto.setBus(toBusDto(reservation.getBus()));

        return dto;
    }

    private static DriverDTO toDriverDto(Driver driver) {
        if (driver == null) {
            return null;
        }

        DriverDTO dto = new DriverDTO();
        dto.setId(driver.getId());
        dto.setFirstName(driver.getFirstName());
        // Map other fields as necessary

        return dto;
    }

    private static ClientDTO toClientDto(Client client) {
        if (client == null) {
            return null;
        }

        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setFirstName(client.getFirstName());
        // Map other fields as necessary

        return dto;
    }

    private static BusDTO toBusDto(Bus bus) {
        if (bus == null) {
            return null;
        }

        BusDTO dto = new BusDTO();
        dto.setId(bus.getId());
        dto.setType(bus.getBusType());


        return dto;
    }
}
