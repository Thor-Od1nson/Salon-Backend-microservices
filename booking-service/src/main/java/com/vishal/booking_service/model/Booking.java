package com.vishal.booking_service.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID bookingId;

    private UUID customerId;
    private UUID salonId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @ElementCollection
    private Set<UUID> serviceIds;
    private int total;
    private BookingStatus status = BookingStatus.PENDING;

    public Booking() {}

    public Booking(UUID bookingId, UUID customerId, UUID salonId, LocalDateTime startTime, LocalDateTime endTime, Set<UUID> serviceIds, int totalServices, BookingStatus status) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.salonId = salonId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.serviceIds = serviceIds;
        this.total = totalServices;
        this.status = status;
    }

    public UUID getBookingId() {
        return bookingId;
    }

    public void setBookingId(UUID bookingId) {
        this.bookingId = bookingId;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public UUID getSalonId() {
        return salonId;
    }

    public void setSalonId(UUID salonId) {
        this.salonId = salonId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Set<UUID> getServiceIds() {
        return serviceIds;
    }

    public void setServiceIds(Set<UUID> serviceIds) {
        this.serviceIds = serviceIds;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int totalServices) {
        this.total = totalServices;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }
}
