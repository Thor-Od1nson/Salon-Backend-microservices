package com.vishal.booking_service.mapper;

import com.vishal.booking_service.model.Booking;
import com.vishal.booking_service.payload.dto.BookingDto;

public class BookingMapper {

    public static BookingDto toDto(Booking booking){
        BookingDto bookingDto=new BookingDto();

        bookingDto.setBookingId(booking.getBookingId());
        bookingDto.setCustomerId(booking.getCustomerId());
        bookingDto.setSalonId(booking.getSalonId());
        bookingDto.setStatus(booking.getStatus());
        bookingDto.setServiceIds(booking.getServiceIds());
        bookingDto.setStartTime(booking.getStartTime());
        bookingDto.setEndTime(booking.getEndTime());
        bookingDto.setTotal(booking.getTotal());

        return bookingDto;
    }
}
