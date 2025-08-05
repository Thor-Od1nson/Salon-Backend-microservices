package com.vishal.service_offring_service.service;

import com.vishal.service_offring_service.exception.CategoryNotFoundException;
import com.vishal.service_offring_service.exception.SalonNotFoundException;
import com.vishal.service_offring_service.exception.ServiceOfferingNotFoundException;
import com.vishal.service_offring_service.model.ServiceOffering;
import com.vishal.service_offring_service.payload.dto.CategoryDto;
import com.vishal.service_offring_service.payload.dto.SalonDto;
import com.vishal.service_offring_service.payload.dto.ServiceOfferingDto;

import java.util.Set;
import java.util.UUID;

public interface IServiceOfferingService {

    ServiceOffering createService(SalonDto salonDto, ServiceOfferingDto serviceDto, CategoryDto categoryDto);
    ServiceOffering updateService(UUID serviceId, ServiceOffering serviceOffering) throws ServiceOfferingNotFoundException;
    Set<ServiceOffering> getAllServiceBySalonId(UUID salonId, UUID categoryId) throws SalonNotFoundException, CategoryNotFoundException;
    Set<ServiceOffering> getServiceByIds(Set<UUID> ids);
    ServiceOffering getServiceById(UUID serviceId) throws ServiceOfferingNotFoundException;
    void deleteServiceById(UUID serviceId) throws ServiceOfferingNotFoundException;

}
