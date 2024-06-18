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

    private String clientId;
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
    private int numSeats;
    private ZonedDateTime createdAt;
    private String status;
    private int totalPrice;
    private String paymentMethod;






}
