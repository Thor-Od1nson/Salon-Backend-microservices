package com.vishal.saloon_service.controller;

import com.vishal.saloon_service.exception.SalonNotFoundException;
import com.vishal.saloon_service.feignclient.AuthUtil;
import com.vishal.saloon_service.mapper.SalonMapper;
import com.vishal.saloon_service.model.Salon;
import com.vishal.saloon_service.payload.dto.SalonDto;
import com.vishal.saloon_service.payload.dto.UserDto;
import com.vishal.saloon_service.service.ISalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/salon")
public class SalonController {

    @Autowired
    private ISalonService salonService;
    
    @Autowired
    private AuthUtil authUtil;

    @PreAuthorize(value = "hasRole('SALON')")
    @PostMapping()
    public ResponseEntity<SalonDto> addSalon(@RequestBody SalonDto salonDto){
//        UserDto userDto = new UserDto(UUID.randomUUID(),"","");
        UserDto userDto = authUtil.getCurrentUser();
        Salon salon = salonService.createSalon(salonDto, userDto);
        SalonDto res= SalonMapper.mapToDto(salon);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PatchMapping("/{salonId}")
    @PreAuthorize(value = "hasRole('SALON')")
    public ResponseEntity<SalonDto> updateSalon(@PathVariable UUID salonId,
                                                @RequestBody SalonDto salonDto)
            throws SalonNotFoundException {
//        UserDto userDto = new UserDto(UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6"),"","");
        UserDto userDto = authUtil.getCurrentUser();
        Salon salon = salonService.updateSalon(salonDto, userDto, salonId);
        SalonDto res= SalonMapper.mapToDto(salon);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/{salonId}")
    public ResponseEntity<SalonDto> getSalon(@PathVariable UUID salonId) throws SalonNotFoundException {
        Salon salon = salonService.getSalonById(salonId);
        SalonDto res= SalonMapper.mapToDto(salon);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<SalonDto>> getAllSalon() {
        List<Salon> salon = salonService.getAllSalons();
        List<SalonDto> res= salon.
                stream().map((s) -> {
                    SalonDto salonDto = SalonMapper.mapToDto(s);
                    return salonDto;
                }
            ).toList();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/owner")
    @PreAuthorize(value = "hasRole('SALON')")
    public ResponseEntity<SalonDto> getSalonByOwner() throws SalonNotFoundException {
//        UserDto userDto = new UserDto(UUID.randomUUID(),"","");
        UserDto userDto = authUtil.getCurrentUser();
        Salon salon = salonService.getSalonByOwnerId(userDto.userId());
        SalonDto res= SalonMapper.mapToDto(salon);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<SalonDto>> searchSalon(@RequestParam("city") String city) throws SalonNotFoundException {
        List<Salon> salon = salonService.searchSalonByCity(city);
        List<SalonDto> res= salon.
                stream().map((s) -> {
                            SalonDto salonDto = SalonMapper.mapToDto(s);
                            return salonDto;
                        }
                ).toList();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{salonId}")
    @PreAuthorize(value = "hasRole('SALON')")
    public ResponseEntity<String> deleteSalon(@PathVariable UUID salonId) throws SalonNotFoundException {
        salonService.deleteSalonById(salonId);
        return new ResponseEntity<>("Salon Deleted successfully...", HttpStatus.NO_CONTENT);
    }
}
