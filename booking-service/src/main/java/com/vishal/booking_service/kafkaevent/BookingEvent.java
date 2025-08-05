package com.vishal.booking_service.kafkaevent;

import com.vishal.booking_service.model.BookingStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class BookingEvent {
    private UUID bookingId;
    private String fullName;
    private String salonName;
    private BookingStatus status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String message;

    public BookingEvent() {
    }

    public BookingEvent(UUID bookingId, String fullName, String salonName, BookingStatus status, LocalDateTime startTime, LocalDateTime endTime, String message) {
        this.bookingId = bookingId;
        this.fullName = fullName;
        this.salonName = salonName;
        this.status = status;
        this.startTime = startTime;
        this.endTime = endTime;
        this.message = message;
    }

    public UUID getBookingId() {
        return bookingId;
    }

    public void setBookingId(UUID bookingId) {
        this.bookingId = bookingId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSalonName() {
        return salonName;
    }

    public void setSalonName(String salonName) {
        this.salonName = salonName;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
