package com.Prcato.Controller;

import com.Prcato.Entity.Booking;
import com.Prcato.Payload.BookingDto;
import com.Prcato.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;
        @PostMapping
        public ResponseEntity<String> BookAnAppointment(@RequestBody BookingDto bookingDto) {
            bookingService.BookAnAppointment(bookingDto);
            return new ResponseEntity<>("Booking is confirmed", HttpStatus.CREATED);
        }
    }

