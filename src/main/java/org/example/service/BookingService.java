package org.example.service;

import org.example.model.Booking;
import org.example.repository.BookingRepository;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public void saveBooking(Booking booking) throws IllegalArgumentException {
        // Проверяем, есть ли уже бронирование для данного автомобиля на выбранную дату
        if (!bookingRepository.findByVehicleIdAndBookingDate(booking.getVehicleId(), booking.getBookingDate()).isEmpty()) {
            throw new IllegalArgumentException("This vehicle is already booked for the selected date.");
        }

        bookingRepository.save(booking);
    }
}
