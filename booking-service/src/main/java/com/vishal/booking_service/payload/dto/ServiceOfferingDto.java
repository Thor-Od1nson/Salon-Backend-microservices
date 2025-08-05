package com.vishal.booking_service.payload.dto;

import java.util.UUID;

public class ServiceOfferingDto {

    private UUID serviceId;
    private String serviceName;
    private String description;
    private int price;
    private int duration;
    private UUID salonId;
    private UUID categoryId;
    private String image;
    private boolean available;

    public ServiceOfferingDto() {
    }

    public ServiceOfferingDto(String image, UUID categoryId, UUID salonId, int duration, int price, String description, String serviceName, UUID serviceId, boolean available) {
        this.image = image;
        this.categoryId = categoryId;
        this.salonId = salonId;
        this.duration = duration;
        this.price = price;
        this.description = description;
        this.serviceName = serviceName;
        this.serviceId = serviceId;
        this.available = available;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public UUID getServiceId() {
        return serviceId;
    }

    public void setServiceId(UUID serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public UUID getSalonId() {
        return salonId;
    }

    public void setSalonId(UUID salonId) {
        this.salonId = salonId;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
