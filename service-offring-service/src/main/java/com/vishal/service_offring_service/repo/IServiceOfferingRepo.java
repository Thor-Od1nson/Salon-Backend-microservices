package com.vishal.service_offring_service.repo;

import com.vishal.service_offring_service.model.ServiceOffering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface IServiceOfferingRepo extends JpaRepository<ServiceOffering, UUID> {

    Set<ServiceOffering> findBySalonId(UUID salonId);
}
