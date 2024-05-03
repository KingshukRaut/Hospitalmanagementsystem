package com.Prcato.Repository;

import com.Prcato.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByBookingTimeAndDate(String bookingTime, LocalDate date);

    List<Booking> findByDate(LocalDate today);
}
