package com.vishal.booking_service.client;

import com.vishal.booking_service.exception.SalonNotFoundException;
import com.vishal.booking_service.payload.dto.SalonDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "api-gateway", path = "/api/v1/salon", contextId = "salonServiceClient")
public interface SalonServiceClient {

    @GetMapping("/{salonId}")
    SalonDto getSalon(@PathVariable UUID salonId) throws SalonNotFoundException;
}
