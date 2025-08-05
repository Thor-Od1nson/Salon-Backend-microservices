package com.vishal.booking_service.controller;

import com.vishal.booking_service.client.CategoryClient;
import com.vishal.booking_service.client.SalonServiceClient;
import com.vishal.booking_service.client.ServiceOfferingClient;
import com.vishal.booking_service.client.UserServiceClient;
import com.vishal.booking_service.exception.BookingNotFoundException;
import com.vishal.booking_service.exception.SalonNotFoundException;
import com.vishal.booking_service.feignclient.AuthUtil;
import com.vishal.booking_service.kafkaevent.BookingEvent;
import com.vishal.booking_service.kafkaeventproducer.BookingEventProducer;
import com.vishal.booking_service.mapper.BookingMapper;
import com.vishal.booking_service.model.Booking;
import com.vishal.booking_service.model.BookingStatus;
import com.vishal.booking_service.model.SalonReport;
import com.vishal.booking_service.payload.dto.*;
import com.vishal.booking_service.service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/bookings")
public class BookingController {

    @Autowired
    private IBookingService bookingService;

    @Autowired
    private UserServiceClient userServiceClient;

    @Autowired
    private AuthUtil authUtil;

    @Autowired
    private ServiceOfferingClient serviceOfferingClient;

    @Autowired
    private CategoryClient categoryClient;

    @Autowired
    private SalonServiceClient salonServiceClient;

    @Autowired
    private BookingEventProducer bookingEvent;


    @PostMapping("/{salonId}")
    public ResponseEntity<Booking> createBooking(@PathVariable UUID salonId,
                                                 @RequestParam("serviceId") UUID serviceId,
                                                 @RequestParam("categoryId") UUID categoryId,
                                                 @RequestBody BookingRequest bookingRequest){
        UserDto loggedIn = authUtil.getCurrentUser();

        UserDto user=userServiceClient.findUserByEmail(loggedIn.email());
        ServiceOfferingDto serviceDto = serviceOfferingClient.getServiceById(serviceId);
        CategoryDto categoryDto = categoryClient.getCategoryById(categoryId);

        SalonDto salon=new SalonDto();
        salon.setSalonId(salonId);
        salon.setOpenTime(LocalTime.of(9, 0));   // 9:00 AM
        salon.setCloseTime(LocalTime.of(21, 0)); // 9:00 PM
        Set<ServiceOfferingDto> serviceOfferingDtoSet=new HashSet<>();
//        ServiceOfferingDto serviceDto=new ServiceOfferingDto();
//        serviceDto.setPrice(399);
//        serviceDto.setDuration(45);
//        serviceDto.setServiceName("Hair cut for men");
//        serviceDto.setServiceName("Best hair cutting");
        serviceDto.setCategoryId(categoryDto.getCategoryId());
        serviceOfferingDtoSet.add(serviceDto);
        Booking booking = bookingService.createBooking(bookingRequest, user, salon, serviceOfferingDtoSet);
        SalonDto salon1 = salonServiceClient.getSalon(salonId);
//        bookingEvent.sendBookingCreatedEvent(booking, user.fullName(), salon1.getSalonName());
        return new ResponseEntity<>(booking, HttpStatus.CREATED);
    }

    @GetMapping("/customer/{customerId}")
    @PreAuthorize(value = "hasRole('CUSTOMER')")
    public ResponseEntity<Set<BookingDto>> getBookingsByCustomer(@PathVariable UUID customerId){
        List<Booking> bookings = bookingService.getBookingByCustomer(customerId);
        return new ResponseEntity<>(getBookingsDto(bookings), HttpStatus.OK);
    }

    @GetMapping("/salon/{salonId}")
    @PreAuthorize(value = "hasRole('SALON')")
    public ResponseEntity<Set<BookingDto>> getBookingsBySalon(@PathVariable UUID salonId){
        List<Booking> bookings = bookingService.getBookingBySalon(salonId);
        return new ResponseEntity<>(getBookingsDto(bookings), HttpStatus.OK);
    }

    private Set<BookingDto> getBookingsDto(List<Booking> bookings){
        return bookings.stream()
                .map( b -> {
                    return BookingMapper.toDto(b);
                }).collect(Collectors.toSet());
    }

    @GetMapping("/{bookingId}/status")
    public ResponseEntity<BookingDto> getBookingsById(@PathVariable UUID bookingId) throws BookingNotFoundException {
        Booking bookings = bookingService.getBookingById(bookingId);
        return new ResponseEntity<>(BookingMapper.toDto(bookings), HttpStatus.OK);
    }

    @PatchMapping("/{bookingId}")
    public ResponseEntity<BookingDto> updateBooking(@PathVariable UUID bookingId, @RequestParam BookingStatus status) throws BookingNotFoundException {
        Booking bookings = bookingService.updateBooking(bookingId, status);
        UserDto loggedIn = authUtil.getCurrentUser();
        UserDto user=userServiceClient.findUserByEmail(loggedIn.email());
        SalonDto salon1 = salonServiceClient.getSalon(bookings.getSalonId());
//        bookingEvent.sendBookingStatusChangedEvent(bookings, user.fullName(), salon1.getSalonName());
        return new ResponseEntity<>(BookingMapper.toDto(bookings), HttpStatus.OK);
    }

    @GetMapping("/slot/salon/{salonId}/date")
    @PreAuthorize(value = "hasRole('SALON')")
    public ResponseEntity<List<BookingSlotDtos>> getBookingsSlot(@PathVariable UUID salonId,
                                                                 @RequestParam(required = false) LocalDate date) throws SalonNotFoundException {
        List<Booking> bookings = bookingService.getBookingByDate(date, salonId);
        List<BookingSlotDtos> slotList = bookings.stream()
                .map(b -> {
                    BookingSlotDtos slotDtos = new BookingSlotDtos();
                    slotDtos.setEndTime(b.getEndTime());
                    slotDtos.setStartTime(b.getStartTime());
                    return slotDtos;
                }).toList();
        return new ResponseEntity<>(slotList, HttpStatus.OK);
    }

    @GetMapping("/report/{salonId}")
    @PreAuthorize(value = "hasRole('SALON')")
    public ResponseEntity<SalonReport> getReport(@PathVariable UUID salonId) throws SalonNotFoundException {
        SalonReport report = bookingService.getSalonReport(salonId);

        return new ResponseEntity<>(report, HttpStatus.OK);
    }
}
