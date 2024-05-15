package com.bus;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
//        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex.getMessage());
//        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @ExceptionHandler(ClientNotFoundException.class)
//    public ResponseEntity<Object> handleClientNotFoundException(ClientNotFoundException ex, WebRequest request) {
//        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, "Client Not Found", ex.getMessage());
//        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
//    }
//    @ExceptionHandler(BusNotFoundException.class)
//    public ResponseEntity<String> handleBusNotFoundException(BusNotFoundException e) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bus not found: " + e.getMessage());
//    }
//
//    @ExceptionHandler(ReservationNotFoundException.class)
//    public ResponseEntity<String> handleReservationNotFoundException(ReservationNotFoundException e) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reservation not found: " + e.getMessage());
//    }
}