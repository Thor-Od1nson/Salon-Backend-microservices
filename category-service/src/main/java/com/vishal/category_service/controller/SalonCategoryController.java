package com.vishal.category_service.controller;

import com.vishal.category_service.client.SalonServiceClient;
import com.vishal.category_service.model.Category;
import com.vishal.category_service.payload.dto.SalonDto;
import com.vishal.category_service.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/category/salon")
public class SalonCategoryController {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private SalonServiceClient salonServiceClient;

    @PostMapping()
    @PreAuthorize(value = "hasRole('SALON')")
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
//        SalonDto salonDto = new SalonDto();
//        salonDto.setSalonId(UUID.randomUUID());
        SalonDto salonDto = salonServiceClient.getSalonByOwner();
        Category savedCategory = categoryService.saveCategory(category, salonDto);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    @DeleteMapping("/{categoryId}")
    @PreAuthorize(value = "hasRole('SALON')")
    public ResponseEntity<String> deleteCategory(@PathVariable UUID categoryId){
//        SalonDto salonDto = new SalonDto();
//        salonDto.setSalonId(UUID.fromString("837120e9-cc3e-4214-9fb0-0e66cfd1f2ff"));
        SalonDto salonDto = salonServiceClient.getSalonByOwner();
        categoryService.deleteCategory(categoryId, salonDto.getSalonId());
        return new ResponseEntity<>("Category is deleted - ", HttpStatus.NO_CONTENT);
    }
}
