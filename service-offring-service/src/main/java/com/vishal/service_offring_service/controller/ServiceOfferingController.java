package com.vishal.service_offring_service.controller;

import com.vishal.service_offring_service.exception.CategoryNotFoundException;
import com.vishal.service_offring_service.exception.SalonNotFoundException;
import com.vishal.service_offring_service.exception.ServiceOfferingNotFoundException;
import com.vishal.service_offring_service.model.ServiceOffering;
import com.vishal.service_offring_service.service.IServiceOfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/service-offering")
public class ServiceOfferingController {

    @Autowired
    private IServiceOfferingService serviceOfferingService;

    @GetMapping("/salon/{salonId}")
    public ResponseEntity<Set<ServiceOffering>> getServiceBySalonId(@PathVariable UUID salonId,
                                                                    @RequestParam(required = false) UUID categoryId) throws SalonNotFoundException, CategoryNotFoundException{
        Set<ServiceOffering> allServiceBySalonId = serviceOfferingService.getAllServiceBySalonId(salonId, categoryId);
        return new ResponseEntity<>(allServiceBySalonId, HttpStatus.OK);
    }

    @GetMapping("/{serviceId}")
    public ResponseEntity<ServiceOffering> getServiceById(@PathVariable UUID serviceId ) throws ServiceOfferingNotFoundException{
        ServiceOffering service = serviceOfferingService.getServiceById(serviceId);
        return new ResponseEntity<>(service, HttpStatus.OK);
    }

    @GetMapping("/allService/{serviceIds}")
    public ResponseEntity<Set<ServiceOffering>> getServiceByIds(@PathVariable Set<UUID> serviceIds) throws ServiceOfferingNotFoundException{
        Set<ServiceOffering> allServiceBy = serviceOfferingService.getServiceByIds(serviceIds);
        return new ResponseEntity<>(allServiceBy, HttpStatus.OK);
    }

}
