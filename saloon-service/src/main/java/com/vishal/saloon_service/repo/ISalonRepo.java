package com.vishal.saloon_service.repo;

import com.vishal.saloon_service.model.Salon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ISalonRepo extends JpaRepository<Salon, UUID> {

    Optional<Salon> findByOwnerId(UUID uuid);

    @Query(
            "SELECT s FROM Salon s WHERE " +
                    "LOWER(s.city) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                    "LOWER(s.salonName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                    "LOWER(s.address) LIKE LOWER(CONCAT('%', :keyword, '%'))"
    )
    List<Salon> searchSalon(@Param("keyword") String keyword);

}
