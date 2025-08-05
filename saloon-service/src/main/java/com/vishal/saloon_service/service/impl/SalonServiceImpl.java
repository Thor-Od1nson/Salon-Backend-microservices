package com.vishal.saloon_service.service.impl;

import com.vishal.saloon_service.exception.SalonNotFoundException;
import com.vishal.saloon_service.model.Salon;
import com.vishal.saloon_service.payload.dto.SalonDto;
import com.vishal.saloon_service.payload.dto.UserDto;
import com.vishal.saloon_service.repo.ISalonRepo;
import com.vishal.saloon_service.service.ISalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SalonServiceImpl implements ISalonService {

    @Autowired
    private ISalonRepo salonRepo;

    @Override
    public Salon createSalon(SalonDto req, UserDto user) {
        Salon salon = new Salon();
        salon.setSalonName(req.getSalonName());
        salon.setAddress(req.getAddress());
        salon.setEmail(req.getEmail());
        salon.setCity(req.getCity());
        salon.setImage(req.getImage());
        salon.setOwnerId(user.userId());
        salon.setOpenTime(req.getOpenTime());
        salon.setCloseTime(req.getCloseTime());
        salon.setPhoneNumber(req.getPhoneNumber());
        return salonRepo.save(salon);
    }

    @Override
    public Salon updateSalon(SalonDto req, UserDto user, UUID salonId) throws SalonNotFoundException {
        Optional<Salon> foundSalon = salonRepo.findById(salonId);
        if (foundSalon.isPresent() && req.getOwnerId().equals(user.userId())) {
            Salon salon = foundSalon.get();

            salon.setSalonName(req.getSalonName());
            salon.setAddress(req.getAddress());
            salon.setEmail(req.getEmail());
            salon.setCity(req.getCity());
            salon.setImage(req.getImage());
            salon.setOwnerId(user.userId());
            salon.setOpenTime(req.getOpenTime());
            salon.setCloseTime(req.getCloseTime());
            salon.setPhoneNumber(req.getPhoneNumber());

            return salonRepo.save(salon);
        }
        throw new SalonNotFoundException("salon not found with this salonId:- "+salonId);
    }

    @Override
    public List<Salon> getAllSalons() {
        return salonRepo.findAll();
    }

    @Override
    public Salon getSalonById(UUID salonId) throws SalonNotFoundException {
        Optional<Salon> salon = salonRepo.findById(salonId);
        if (salon.isEmpty()) {
            throw new SalonNotFoundException("Salon not found with this salon id:- "+salonId);
        }
        return salon.get();
    }

    @Override
    public Salon getSalonByOwnerId(UUID ownerId) throws SalonNotFoundException {
        Optional<Salon> byOwnerId = salonRepo.findByOwnerId(ownerId);
        if (byOwnerId.isEmpty()) {
            throw new SalonNotFoundException("Salon not found with this OwnerId:- "+ownerId);
        }
        return byOwnerId.get();
    }

    @Override
    public List<Salon> searchSalonByCity(String city) {
        return salonRepo.searchSalon(city);
    }

    @Override
    public void deleteSalonById(UUID salonId) throws SalonNotFoundException {
        Optional<Salon> salon = salonRepo.findById(salonId);
        if (salon.isEmpty()) {
            throw new SalonNotFoundException("Salon not found with this salon id:- "+salonId);
        }
        salonRepo.deleteById(salonId);
    }
}
