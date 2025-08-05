package com.vishal.service_offring_service.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class ServiceOffering {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID serviceId;

    @Column(nullable = false)
    private String serviceName;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int duration;

    @Column(nullable = false)
    private UUID salonId;

    @Column(nullable = false)
    private UUID categoryId;

    @Column(nullable = false)
    private boolean available;

    private String image;

    public ServiceOffering() {
    }

    public ServiceOffering(String image, UUID categoryId, UUID salonId, int duration, int price, String description, String serviceName, UUID serviceId, boolean available) {
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
