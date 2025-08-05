package com.vishal.saloon_service.mapper;

import com.vishal.saloon_service.model.Salon;
import com.vishal.saloon_service.payload.dto.SalonDto;

public class SalonMapper {

    public static SalonDto mapToDto(Salon salon){
        SalonDto salonDto = new SalonDto();
        salonDto.setSalonId(salon.getSalonId());
        salonDto.setSalonName(salon.getSalonName());
        salonDto.setAddress(salon.getAddress());
        salonDto.setImage(salon.getImage());
        salonDto.setOpenTime(salon.getOpenTime());
        salonDto.setCloseTime(salon.getCloseTime());
        salonDto.setPhoneNumber(salon.getPhoneNumber());
        salonDto.setOwnerId(salon.getOwnerId());
        salonDto.setCity(salon.getCity());
        salonDto.setEmail(salon.getEmail());
        return salonDto;
    }
}
