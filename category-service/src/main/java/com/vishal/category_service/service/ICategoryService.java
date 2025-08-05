package com.vishal.category_service.service;

import com.vishal.category_service.exception.CategoryNotFoundException;
import com.vishal.category_service.exception.SalonNotFoundException;
import com.vishal.category_service.model.Category;
import com.vishal.category_service.payload.dto.SalonDto;

import java.util.Set;
import java.util.UUID;

public interface ICategoryService {

    Category saveCategory(Category category, SalonDto salonDto);
    Set<Category> getAllCategoriesBySalon(UUID salonId) throws SalonNotFoundException;
    Category getCategoryById(UUID categoryId) throws CategoryNotFoundException;
    void deleteCategory(UUID categoryId, UUID salonId) throws CategoryNotFoundException;
}
