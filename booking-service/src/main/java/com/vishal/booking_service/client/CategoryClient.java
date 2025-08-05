package com.vishal.booking_service.client;

import com.vishal.booking_service.payload.dto.CategoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "api-gateway", path = "/api/v1/category", contextId = "categoryClient")
public interface CategoryClient {

    @GetMapping("/{categoryId}")
    CategoryDto getCategoryById(@PathVariable UUID categoryId);
}
