package com.vishal.service_offring_service.controller;

import com.vishal.service_offring_service.client.SalonServiceClient;
import com.vishal.service_offring_service.exception.CategoryNotFoundException;
import com.vishal.service_offring_service.exception.SalonNotFoundException;
import com.vishal.service_offring_service.exception.ServiceOfferingNotFoundException;
import com.vishal.service_offring_service.model.ServiceOffering;
import com.vishal.service_offring_service.payload.dto.CategoryDto;
import com.vishal.service_offring_service.payload.dto.SalonDto;
import com.vishal.service_offring_service.payload.dto.ServiceOfferingDto;
import com.vishal.service_offring_service.service.IServiceOfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/salon/service-offering")
public class SalonServiceOfferingController {

    @Autowired
    private IServiceOfferingService serviceOfferingService;

    @Autowired
    private SalonServiceClient salonServiceClient;

    @PreAuthorize(value = "hasRole('SALON')")
    @PostMapping()
    public ResponseEntity<ServiceOffering> addService(@RequestBody ServiceOfferingDto serviceOfferingDto){
//        SalonDto salonDto=new SalonDto();
//        salonDto.setSalonId(UUID.randomUUID());
        SalonDto salonDto = salonServiceClient.getSalonByOwner();
        System.out.println(salonDto.getSalonId());

        CategoryDto categoryDto=new CategoryDto();
        categoryDto.setCategoryId(serviceOfferingDto.getCategoryId());
        ServiceOffering service = serviceOfferingService.createService(salonDto, serviceOfferingDto, categoryDto);
        return new ResponseEntity<>(service, HttpStatus.CREATED);
    }

    @PreAuthorize(value = "hasRole('SALON')")
    @PatchMapping("/{serviceId}")
    public ResponseEntity<ServiceOffering> updateService(@PathVariable UUID serviceId, @RequestBody ServiceOffering serviceOffering){
        ServiceOffering service = serviceOfferingService.updateService(serviceId, serviceOffering);
        return new ResponseEntity<>(service, HttpStatus.OK);
    }

    @PreAuthorize(value = "hasAnyRole('SALON')")
    @DeleteMapping("/{serviceId}")
    public ResponseEntity<String> deleteService(@PathVariable UUID serviceId) throws ServiceOfferingNotFoundException{
        serviceOfferingService.deleteServiceById(serviceId);
        return new ResponseEntity<>("Service deleted!!!..", HttpStatus.NO_CONTENT);
    }
}
