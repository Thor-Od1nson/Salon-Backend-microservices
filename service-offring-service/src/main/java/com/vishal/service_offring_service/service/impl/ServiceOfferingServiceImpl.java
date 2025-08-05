package com.vishal.service_offring_service.service.impl;

import com.vishal.service_offring_service.client.SalonServiceClient;
import com.vishal.service_offring_service.exception.CategoryNotFoundException;
import com.vishal.service_offring_service.exception.SalonNotFoundException;
import com.vishal.service_offring_service.exception.ServiceOfferingNotFoundException;
import com.vishal.service_offring_service.model.ServiceOffering;
import com.vishal.service_offring_service.payload.dto.CategoryDto;
import com.vishal.service_offring_service.payload.dto.SalonDto;
import com.vishal.service_offring_service.payload.dto.ServiceOfferingDto;
import com.vishal.service_offring_service.repo.IServiceOfferingRepo;
import com.vishal.service_offring_service.service.IServiceOfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ServiceOfferingServiceImpl implements IServiceOfferingService {

    @Autowired
    private IServiceOfferingRepo serviceOfferingRepo;

    @Override
    public ServiceOffering createService(SalonDto salonDto, ServiceOfferingDto serviceDto, CategoryDto categoryDto) {
        ServiceOffering serviceOffering = new ServiceOffering();
        serviceOffering.setImage(serviceDto.getImage());
        serviceOffering.setSalonId(salonDto.getSalonId());
        serviceOffering.setAvailable(serviceDto.isAvailable());
        serviceOffering.setServiceName(serviceDto.getServiceName());
        serviceOffering.setDescription(serviceDto.getDescription());
        serviceOffering.setCategoryId(categoryDto.getCategoryId());
        serviceOffering.setPrice(serviceDto.getPrice());
        serviceOffering.setDuration(serviceDto.getDuration());
        return serviceOfferingRepo.save(serviceOffering);
    }

    @Override
    public ServiceOffering updateService(UUID serviceId, ServiceOffering service) throws ServiceOfferingNotFoundException {
        Optional<ServiceOffering> foundService = serviceOfferingRepo.findById(serviceId);
        if (foundService.isEmpty()) {
            throw new ServiceOfferingNotFoundException("Service not found with this id- "+serviceId);
        }
        ServiceOffering serviceOffering = foundService.get();

        serviceOffering.setImage(service.getImage());
        serviceOffering.setServiceName(service.getServiceName());
        serviceOffering.setDescription(service.getDescription());
        serviceOffering.setPrice(service.getPrice());
        serviceOffering.setAvailable(service.isAvailable());
        serviceOffering.setDuration(service.getDuration());
        return serviceOfferingRepo.save(serviceOffering);
    }

    @Override
    public Set<ServiceOffering> getAllServiceBySalonId(UUID salonId, UUID categoryId) throws SalonNotFoundException, CategoryNotFoundException {
        Set<ServiceOffering> services = serviceOfferingRepo.findBySalonId(salonId);
        if (categoryId!=null){
            services = services.stream()
                    .filter((service) -> service.getCategoryId() != null &&
                            service.getCategoryId()==categoryId).collect(Collectors.toSet());
        }
        return services;
    }

    @Override
    public Set<ServiceOffering> getServiceByIds(Set<UUID> ids) {
        List<ServiceOffering> services = serviceOfferingRepo.findAllById(ids);
        return new HashSet<>(services);
    }

    @Override
    public ServiceOffering getServiceById(UUID serviceId) throws ServiceOfferingNotFoundException {
        Optional<ServiceOffering> service = serviceOfferingRepo.findById(serviceId);
        if (service.isEmpty()) {
            throw new ServiceOfferingNotFoundException("Service not found with this id - "+serviceId);
        }
        return service.get();
    }

    @Override
    public void deleteServiceById(UUID serviceId) throws ServiceOfferingNotFoundException {
        Optional<ServiceOffering> service = serviceOfferingRepo.findById(serviceId);
        if (service.isEmpty()) {
            throw new ServiceOfferingNotFoundException("Service not found with this id - "+serviceId);
        }
        serviceOfferingRepo.deleteById(serviceId);
    }
}
