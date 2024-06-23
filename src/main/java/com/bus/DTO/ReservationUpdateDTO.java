package com.bus.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationUpdateDTO {
    private Long reservationId;
    private Long busId;
    private Long driverId;
    private Integer totalPrice;
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
    private Boolean isVerified;
}