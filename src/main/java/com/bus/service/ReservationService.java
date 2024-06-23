package com.bus.service;

import com.bus.DTO.*;
import com.bus.ResourceNotFoundException;
import com.bus.entity.Bus;
import com.bus.entity.Client;
import com.bus.entity.Driver;
import com.bus.entity.Reservation;
import com.bus.repository.BusRepository;
import com.bus.repository.ClientRepository;
import com.bus.repository.DriverRepository;
import com.bus.repository.ReservationRepository;
import com.bus.request.ReservationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    @Autowired
    public ClientRepository clientRepository;
    @Autowired
    private ReservationRepository reservationRepository;
   @Autowired
    public DriverRepository driverRepository;
    @Autowired
   private BusRepository busRepository ;
    @Cacheable(value = "reservation")
    public List<ReservationDTO> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream()
                .map(ReservationMapper::toDto)
                .collect(Collectors.toList());
    }


    public void addReservation(ReservationRequest reservationRequest) {

        Client client = clientRepository.findById(reservationRequest.getClient_id())
                .orElseThrow(() -> new RuntimeException("Client not found"));
/*
        Bus bus = null;
        if (reservationRequest.getBus_id() != null) {
            bus = busRepository.findById(reservationRequest.getBus_id())
                    .orElseThrow(() -> new RuntimeException("Bus not found"));
        }

        Driver driver = driverRepository.findById(reservationRequest.getDriver_id())
                .orElseThrow(() -> new RuntimeException("Client not found"));
*/
        Reservation reservation = new Reservation();
        reservation.setCIN(reservationRequest.getCIN());
        reservation.setStartDate(reservationRequest.getDateDepart());
        reservation.setEndDate(reservationRequest.getDateRetour());
        reservation.setCreatedAt(ZonedDateTime.now());
        reservation.setStatus("waiting");
        reservation.setClient(client);
        reservation.setBus_type(reservationRequest.getBusType());
        reservation.setDeparture_place(reservationRequest.getLieuDepart());
        reservation.setDestination(reservationRequest.getDestination());
        reservation.setCompanion_firstname(reservationRequest.getNomCompanion());
        reservation.setCompanion_lastname(reservationRequest.getPrenomCompanion());
        reservation.setCompanion_phone(reservationRequest.getPhone());
        reservation.setTrip_utility(reservationRequest.getTripUtility());
        reservation.setClient_category(reservationRequest.getPersontype());
        reservation.setNumSeats(reservationRequest.getNum_seats());
        reservation.setVerified(false);
//        reservation.setBus(bus);
//        reservation.setDriver(driver);

    //    System.out.println("Received reservation request: " + reservation);
        reservationRepository.save(reservation);
    }

    public ReservationDTO getReservationById(Long id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        if (reservation.isPresent()) {
            return ReservationMapper.toDto(reservation.get());
        } else {
            throw new ResourceNotFoundException("Reservation not found with ID " + id);
        }
    }

    public List<ReservationDTO> getReservationsByClientId(Long clientId) {
        List<Reservation> reservations = reservationRepository.findByClientId(clientId);
        return reservations.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    private ReservationDTO convertToDto(Reservation reservation) {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setId(reservation.getId());
        reservationDTO.setStartDate(reservation.getStartDate());
        reservationDTO.setEndDate(reservation.getEndDate());
        reservationDTO.setTotalPrice(reservation.getTotalPrice());
        reservationDTO.setNumSeats(reservation.getNumSeats());
        reservationDTO.setCreatedAt(reservation.getCreatedAt());
        reservationDTO.setStatus(reservation.getStatus());
        reservationDTO.setPaymentMethod(reservation.getPaymentMethod());
        reservationDTO.setPaymentStatus(reservation.getPaymentStatus());

        // Map Driver
        if (reservation.getDriver() != null) {
            DriverDTO driverDTO = new DriverDTO();
            driverDTO.setId(reservation.getDriver().getId());
            driverDTO.setFirstName(reservation.getDriver().getFirstName());

            reservationDTO.setDriver(driverDTO);
        }

        // Map Client
        if (reservation.getClient() != null) {
            ClientDTO clientDTO = new ClientDTO();
            clientDTO.setId(reservation.getClient().getId());
            clientDTO.setFirstName(reservation.getClient().getFirstName());
            clientDTO.setLastName(reservation.getClient().getLastName());

            reservationDTO.setClient(clientDTO);
        }

        // Map Bus
        if (reservation.getBus() != null) {
            BusDTO busDTO = new BusDTO();
            busDTO.setId(reservation.getBus().getId());
            busDTO.setNumPlate(reservation.getBus().getNumPlate());

            reservationDTO.setBus(busDTO);
        }

        reservationDTO.setCIN(reservation.getCIN());
        reservationDTO.setBus_type(reservation.getBus_type());
        reservationDTO.setDeparture_place(reservation.getDeparture_place());
        reservationDTO.setDestination(reservation.getDestination());
        reservationDTO.setCompanion_firstname(reservation.getCompanion_firstname());
        reservationDTO.setCompanion_lastname(reservation.getCompanion_lastname());
        reservationDTO.setCompanion_phone(reservation.getCompanion_phone());
        reservationDTO.setTrip_utility(reservation.getTrip_utility());
        reservationDTO.setClient_category(reservation.getClient_category());
        reservationDTO.setVerified(reservation.isVerified());

        return reservationDTO;
    }

    public ReservationDTO updateReservation(ReservationUpdateDTO reservationUpdateDTO) {
        Reservation reservation = reservationRepository.findById(reservationUpdateDTO.getReservationId())
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));

        if (reservationUpdateDTO.getBusId() != null) {
            Bus bus = busRepository.findById(reservationUpdateDTO.getBusId())
                    .orElseThrow(() -> new IllegalArgumentException("Bus not found"));
            reservation.setBus(bus);
        }
        if (reservationUpdateDTO.getDriverId() != null) {
            Driver driver = driverRepository.findById(reservationUpdateDTO.getDriverId())
                    .orElseThrow(() -> new IllegalArgumentException("Driver not found"));
            reservation.setDriver(driver);
        }
        if (reservationUpdateDTO.getTotalPrice() != null) {
            reservation.setTotalPrice(reservationUpdateDTO.getTotalPrice());
        }
        if (reservationUpdateDTO.getStartDate() != null) {
            reservation.setStartDate(reservationUpdateDTO.getStartDate());
        }
        if (reservationUpdateDTO.getEndDate() != null) {
            reservation.setEndDate(reservationUpdateDTO.getEndDate());
        }
        if (reservationUpdateDTO.getIsVerified() != null) {
            reservation.setVerified(reservationUpdateDTO.getIsVerified());
        }
        Reservation updatedReservation = reservationRepository.save(reservation);
        return convertToDto(updatedReservation);
    }

    public List<ReservationDTO> getReservationsByDriverId(Long driverId) {
        List<Reservation> reservations = reservationRepository.findByDriverId(driverId);
        return reservations.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // Method to fetch reservation details and available buses
    public ReservationDetailsDTO getReservationDetailsWithAvailableBuses(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found"));

        ZonedDateTime startDate = reservation.getStartDate();
        ZonedDateTime endDate = reservation.getEndDate();

        List<Bus> availableBuses = busRepository.findAvailableBuses(startDate, endDate);

        // Convert entities to DTOs as needed
        ReservationDTO reservationDTO = convertToDto(reservation);
        List<BusDTO> availableBusDTOs = availableBuses.stream()
                .map(this::convertBusToDto)
                .collect(Collectors.toList());

        // Create and return a DTO containing reservation details and available buses
        return new ReservationDetailsDTO(reservationDTO, availableBusDTOs);
    }

    private BusDTO convertBusToDto(Bus bus) {
        if (bus == null) {
            return null;
        }
        BusDTO busDTO = new BusDTO();
        busDTO.setId(bus.getId());
        busDTO.setType(bus.getBusType());
        busDTO.setNumPlate(bus.getNumPlate());
        return busDTO;
    }

}


