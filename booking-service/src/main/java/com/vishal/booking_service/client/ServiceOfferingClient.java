package com.vishal.booking_service.client;

import com.vishal.booking_service.payload.dto.ServiceOfferingDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "api-gateway", path = "/api/v1/service-offering", contextId = "serviceOfferingClient")
public interface ServiceOfferingClient {

    @GetMapping("/{serviceId}")
    ServiceOfferingDto getServiceById(@PathVariable UUID serviceId);
}
