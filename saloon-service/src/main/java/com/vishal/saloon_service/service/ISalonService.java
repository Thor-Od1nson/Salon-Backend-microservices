package com.vishal.saloon_service.service;

import com.vishal.saloon_service.exception.SalonNotFoundException;
import com.vishal.saloon_service.model.Salon;
import com.vishal.saloon_service.payload.dto.SalonDto;
import com.vishal.saloon_service.payload.dto.UserDto;

import java.util.List;
import java.util.UUID;

public interface ISalonService {
    Salon createSalon(SalonDto salon, UserDto user);
    Salon updateSalon(SalonDto salon, UserDto user, UUID salonId) throws SalonNotFoundException;
    List<Salon> getAllSalons();
    Salon getSalonById(UUID salonId) throws SalonNotFoundException;
    Salon getSalonByOwnerId(UUID ownerId) throws SalonNotFoundException;
    List<Salon> searchSalonByCity(String city);
    void deleteSalonById(UUID salonId) throws SalonNotFoundException;
//    List<Salon>

}
