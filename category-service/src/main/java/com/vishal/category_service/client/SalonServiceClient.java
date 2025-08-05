package com.vishal.category_service.client;


import com.vishal.category_service.exception.SalonNotFoundException;
import com.vishal.category_service.payload.dto.SalonDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "api-gateway", path = "/api/v1/salon", contextId = "salonServiceClient")
public interface SalonServiceClient {

    @GetMapping("/owner")
    SalonDto getSalonByOwner() throws SalonNotFoundException;
}
