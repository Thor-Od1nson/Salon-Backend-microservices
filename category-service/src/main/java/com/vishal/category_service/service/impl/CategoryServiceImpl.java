package com.vishal.category_service.service.impl;

import com.vishal.category_service.exception.CategoryNotFoundException;
import com.vishal.category_service.exception.SalonNotFoundException;
import com.vishal.category_service.model.Category;
import com.vishal.category_service.payload.dto.SalonDto;
import com.vishal.category_service.repo.ICategoryRepo;
import com.vishal.category_service.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryRepo categoryRepo;

    @Override
    public Category saveCategory(Category category, SalonDto salonDto) {
        Category newCategory = new Category();
        newCategory.setCategoryName(category.getCategoryName());
        newCategory.setImage(category.getImage());
        newCategory.setSalonId(salonDto.getSalonId());
        return categoryRepo.save(newCategory);
    }

    @Override
    public Set<Category> getAllCategoriesBySalon(UUID salonId) throws SalonNotFoundException {
        return categoryRepo.findBySalonId(salonId);
    }

    @Override
    public Category getCategoryById(UUID categoryId) throws CategoryNotFoundException {
        Optional<Category> category = categoryRepo.findById(categoryId);
        if (category.isEmpty()) {
            throw new CategoryNotFoundException("Category with this id is not found - "+categoryId);
        }
        return category.get();
    }

    @Override
    public void deleteCategory(UUID categoryId, UUID salonId) throws CategoryNotFoundException, SalonNotFoundException {
        Optional<Category> category = categoryRepo.findById(categoryId);
        if (category.isEmpty()) {
            throw new CategoryNotFoundException("Category with this id is not found - "+categoryId);
        }
        if (!category.get().getSalonId().equals(salonId)) {
            throw new SalonNotFoundException("Salon not found");
        }
        categoryRepo.deleteById(categoryId);
    }
}
