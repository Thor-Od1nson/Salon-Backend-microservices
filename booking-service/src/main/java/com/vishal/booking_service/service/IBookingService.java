package com.vishal.booking_service.service;

import com.vishal.booking_service.exception.BookingNotFoundException;
import com.vishal.booking_service.exception.SalonNotFoundException;
import com.vishal.booking_service.model.Booking;
import com.vishal.booking_service.model.BookingStatus;
import com.vishal.booking_service.model.SalonReport;
import com.vishal.booking_service.payload.dto.BookingRequest;
import com.vishal.booking_service.payload.dto.SalonDto;
import com.vishal.booking_service.payload.dto.ServiceOfferingDto;
import com.vishal.booking_service.payload.dto.UserDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface IBookingService {

    Booking createBooking(BookingRequest bookingRequest,
                          UserDto userDto,
                          SalonDto salonDto,
                          Set<ServiceOfferingDto> serviceDto);

    List<Booking> getBookingByCustomer(UUID customerId);
    List<Booking> getBookingBySalon(UUID salonId);
    Booking getBookingById(UUID bookingId) throws BookingNotFoundException;
    Booking updateBooking(UUID bookingId, BookingStatus status);
    List<Booking> getBookingByDate(LocalDate date, UUID salonId);
    SalonReport getSalonReport(UUID salonId) throws SalonNotFoundException;
}
