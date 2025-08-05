package com.vishal.payment_service.payload.dto;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public class BookingDto {
    private UUID bookingId;
    private UUID customerId;
    private UUID salonId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Set<UUID> serviceIds;
    private int total;

    public BookingDto() {}

    public BookingDto(UUID bookingId, UUID customerId, UUID salonId, LocalDateTime startTime, LocalDateTime endTime, Set<UUID> serviceIds, int totalServices) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.salonId = salonId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.serviceIds = serviceIds;
        this.total = totalServices;
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
}
