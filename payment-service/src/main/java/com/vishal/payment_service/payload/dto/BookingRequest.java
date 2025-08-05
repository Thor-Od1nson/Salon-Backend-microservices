package com.vishal.payment_service.payload.dto;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public class BookingRequest {

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Set<UUID> serviceIds;

    public BookingRequest() {
    }

    public BookingRequest(LocalDateTime startTime, LocalDateTime endTime, Set<UUID> serviceIds) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.serviceIds = serviceIds;
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
}
