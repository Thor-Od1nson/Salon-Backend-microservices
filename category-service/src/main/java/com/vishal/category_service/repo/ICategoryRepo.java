package com.vishal.category_service.repo;

import com.vishal.category_service.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface ICategoryRepo extends JpaRepository<Category, UUID> {

    Set<Category> findBySalonId(UUID salonId);
}
