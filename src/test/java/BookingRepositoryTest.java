

import org.example.RentalApplication;
import org.example.model.Booking;
import org.example.repository.BookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = RentalApplication.class)
public class BookingRepositoryTest {

    @Autowired
    private BookingRepository bookingRepository;

    private Booking booking;

    @BeforeEach
    public void setUp() {
        booking = new Booking();
        booking.setVehicleId(1L);
        booking.setUserId(2L);
        booking.setBookingDate(LocalDate.of(2024, 12, 6));
    }

    @Test
    public void testSaveBooking() {
        Booking savedBooking = bookingRepository.save(booking);
        assertNotNull(savedBooking.getId(), "Booking ID should not be null after saving");
        assertEquals(1L, savedBooking.getVehicleId(), "Vehicle ID should be correct");
        assertEquals(2L, savedBooking.getUserId(), "User ID should be correct");
        assertEquals(LocalDate.of(2024, 12, 6), savedBooking.getBookingDate(), "Booking date should be correct");
    }

    @Test
    public void testFindBookingById() {
        Booking savedBooking = bookingRepository.save(booking);
        Booking foundBooking = bookingRepository.findById(savedBooking.getId()).orElse(null);

        assertNotNull(foundBooking, "Booking should be found by ID");
        assertEquals(savedBooking.getId(), foundBooking.getId(), "Booking IDs should match");
    }


    @Test
    public void testDeleteBooking() {
        Booking savedBooking = bookingRepository.save(booking);
        bookingRepository.delete(savedBooking);

        Booking foundBooking = bookingRepository.findById(savedBooking.getId()).orElse(null);
        assertEquals(null, foundBooking, "Booking should be deleted from the repository");
    }
}
