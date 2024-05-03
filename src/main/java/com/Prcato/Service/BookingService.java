package com.Prcato.Service;
import com.Prcato.Entity.Booking;
import com.Prcato.Payload.BookingDto;
import com.Prcato.Repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    // Enum for time slots
    public enum TimeSlot {
        SLOT_10_50_AM("10:50 AM"),
        SLOT_11_50_AM("11:50 AM"),
        SLOT_12_50_PM("12:50 PM"),
        SLOT_03_50_PM("03:50 PM"),
        SLOT_04_50_PM("04:50 PM");

        private final String time;

        TimeSlot(String time) {
            this.time = time;
        }

        public String getTime() {
            return time;
        }
    }
    // Scheduled task to generate time slots every 24 hours
    @Scheduled(fixedRate = 24 * 60 * 60 * 1000) // Run every 24 hours
    public void generateTimeSlots() {
        LocalDate today = LocalDate.now();
        List<Booking> existingBookings = bookingRepository.findByDate(today);

        // If no bookings exist for today, generate time slots
        if (existingBookings.isEmpty()) {
            List<Booking> timeSlots = new ArrayList<>();
            for (TimeSlot slot : TimeSlot.values()) {
                Booking timeSlot = new Booking();
                timeSlot.setDate(today);
                timeSlot.setBookingTime(slot.getTime());
                timeSlots.add(timeSlot);
            }
            bookingRepository.saveAll(timeSlots);
        }
    }

    public void BookAnAppointment(BookingDto bookingDto) {
        // Check if the desired time slot is available
        if (!isTimeSlotAvailable(bookingDto.getBookingTime())) {
            System.out.println("Time Slot Not Available");
            return; // Exit the method if the time slot is not available
        }

        // Create a new booking
        Booking booking = new Booking();
        booking.setDate(LocalDate.now());
        booking.setBookingTime(bookingDto.getBookingTime());
        booking.setDoctorId(bookingDto.getDoctorId());
        booking.setPatientId(bookingDto.getPatientId());

        // Save the booking
        bookingRepository.save(booking);
    }

    // Check if the specified time slot is available
    private boolean isTimeSlotAvailable(String bookingTime) {
        LocalDate today = LocalDate.now();
        List<Booking> bookings = bookingRepository.findByBookingTimeAndDate(bookingTime, today);
        return bookings.isEmpty();
    }
}
