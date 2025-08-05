package com.vishal.booking_service.model;

import java.util.UUID;

public class SalonReport {

    private UUID salonId;
    private String salonName;
    private Double totalEarnings;
    private Integer totalBookings;
    private Integer cancelBookings;
    private Double totalRefund;

    public SalonReport() {
    }

    public SalonReport(UUID salonId, String salonName, Double totalEarnings, Integer totalBookings, Integer cancelBookings, Double totalRefund) {
        this.salonId = salonId;
        this.salonName = salonName;
        this.totalEarnings = totalEarnings;
        this.totalBookings = totalBookings;
        this.cancelBookings = cancelBookings;
        this.totalRefund = totalRefund;
    }

    public UUID getSalonId() {
        return salonId;
    }

    public void setSalonId(UUID salonId) {
        this.salonId = salonId;
    }

    public String getSalonName() {
        return salonName;
    }

    public void setSalonName(String salonName) {
        this.salonName = salonName;
    }

    public Double getTotalEarnings() {
        return totalEarnings;
    }

    public void setTotalEarnings(Double totalEarnings) {
        this.totalEarnings = totalEarnings;
    }

    public Integer getTotalBookings() {
        return totalBookings;
    }

    public void setTotalBookings(Integer totalBookings) {
        this.totalBookings = totalBookings;
    }

    public Integer getCancelBookings() {
        return cancelBookings;
    }

    public void setCancelBookings(Integer cancelBookings) {
        this.cancelBookings = cancelBookings;
    }

    public Double getTotalRefund() {
        return totalRefund;
    }

    public void setTotalRefund(Double totalRefund) {
        this.totalRefund = totalRefund;
    }
}
