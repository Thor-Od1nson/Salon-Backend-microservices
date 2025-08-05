package com.vishal.payment_service.payload.dto;

import java.time.LocalDateTime;

public class BookingSlotDtos {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public BookingSlotDtos() {
    }

    public BookingSlotDtos(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
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
}
