package com.vishal.booking_service.repo;

import com.vishal.booking_service.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IBookingRepo extends JpaRepository<Booking, UUID> {

    List<Booking> findByCustomerId(UUID customerId);
    List<Booking> findBySalonId(UUID salonId);
}
