package com.vishal.category_service.controller;

import com.vishal.category_service.exception.CategoryNotFoundException;
import com.vishal.category_service.exception.SalonNotFoundException;
import com.vishal.category_service.model.Category;
import com.vishal.category_service.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/salon/{salonId}")
    public ResponseEntity<Set<Category>> getCategoriesBySalon(@PathVariable UUID salonId) throws SalonNotFoundException{
        Set<Category> allCategoriesBySalon = categoryService.getAllCategoriesBySalon(salonId);
        return new ResponseEntity<>(allCategoriesBySalon, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable UUID categoryId) throws CategoryNotFoundException {
        Category categoryById = categoryService.getCategoryById(categoryId);
        return new ResponseEntity<>(categoryById, HttpStatus.OK);
    }


}
