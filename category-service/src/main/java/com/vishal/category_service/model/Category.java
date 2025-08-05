package com.vishal.category_service.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID categoryId;

    @Column(nullable = false)
    private String categoryName;
    private String image;

    @Column(nullable = false)
    private UUID salonId;

    public Category() {
    }

    public Category(UUID categoryId, String categoryName, String image, UUID salonId) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.image = image;
        this.salonId = salonId;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public UUID getSalonId() {
        return salonId;
    }

    public void setSalonId(UUID salonId) {
        this.salonId = salonId;
    }
}
