package com.vishal.service_offring_service.payload.dto;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public class SalonDto {

    private UUID salonId;
    private String salonName;
    private List<String> image;
    private String address;
    private String phoneNumber;
    private String email;
    private String city;
    private UUID ownerId;
    private LocalTime openTime;
    private LocalTime closeTime;

    public SalonDto() {
    }

    public SalonDto(UUID salonId, String salonName, List<String> image, String address, String phoneNumber, String city, UUID ownerId, LocalTime openTime, LocalTime closeTime, String email) {
        this.salonId = salonId;
        this.salonName = salonName;
        this.image = image;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.ownerId = ownerId;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }

    public LocalTime getOpenTime() {
        return openTime;
    }

    public void setOpenTime(LocalTime openTime) {
        this.openTime = openTime;
    }

    public LocalTime getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(LocalTime closeTime) {
        this.closeTime = closeTime;
    }

}
