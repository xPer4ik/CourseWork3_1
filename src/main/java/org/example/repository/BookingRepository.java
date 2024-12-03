package org.example.repository;

import org.example.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByVehicleId(Long vehicleId);
    List<Booking> findByUserId(Long userId);
}

