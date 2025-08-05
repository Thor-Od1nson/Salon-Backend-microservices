package com.vishal.booking_service.service.impl;

import com.vishal.booking_service.exception.BookingNotFoundException;
import com.vishal.booking_service.exception.SalonNotFoundException;
import com.vishal.booking_service.kafkaevent.BookingEvent;
import com.vishal.booking_service.model.Booking;
import com.vishal.booking_service.model.BookingStatus;
import com.vishal.booking_service.model.SalonReport;
import com.vishal.booking_service.payload.dto.BookingRequest;
import com.vishal.booking_service.payload.dto.SalonDto;
import com.vishal.booking_service.payload.dto.ServiceOfferingDto;
import com.vishal.booking_service.payload.dto.UserDto;
import com.vishal.booking_service.repo.IBookingRepo;
import com.vishal.booking_service.service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements IBookingService {

    @Autowired
    private IBookingRepo bookingRepo;

    public Boolean isTimeSlotAvailable(SalonDto salonDto,
                                       LocalDateTime bookingStartTime,
                                       LocalDateTime bookingEndTime){

        List<Booking> existingBookings=getBookingBySalon(salonDto.getSalonId());

        LocalDateTime salonOpenTime = salonDto.getOpenTime().atDate(bookingStartTime.toLocalDate());
        LocalDateTime salonCloseTime = salonDto.getCloseTime().atDate(bookingStartTime.toLocalDate());

        if (bookingStartTime.isBefore(salonOpenTime) || bookingEndTime.isAfter(salonCloseTime)){
            throw new BookingNotFoundException("Booking time must be within salon working hours."+"OPENING TIME - "+salonOpenTime+" CLOSING TIME "+salonCloseTime);
        }

        for(Booking existingBooking : existingBookings){
            LocalDateTime existingBookingStartTime=existingBooking.getStartTime();
            LocalDateTime existingBookingEndTime=existingBooking.getEndTime();

            if (bookingStartTime.isBefore(existingBookingStartTime) && bookingEndTime.isAfter(existingBookingEndTime)){
                throw new BookingNotFoundException("Slot not available, Choose different time slots.");
            }

            if (bookingStartTime.isEqual(existingBookingStartTime) && bookingEndTime.isEqual(existingBookingEndTime)){
                throw new BookingNotFoundException("Slot not available, Choose different time slots.");
            }
        }
        return true;
    }

    @Override
    public Booking createBooking(BookingRequest bookingRequest,
                                 UserDto userDto,
                                 SalonDto salonDto,
                                 Set<ServiceOfferingDto> serviceDto) {

        int totalDurations = serviceDto.stream()
                .mapToInt(ServiceOfferingDto::getDuration)
                .sum();
        LocalDateTime bookingStartTime = bookingRequest.getStartTime();
        LocalDateTime bookingEndTime = bookingStartTime.plusMinutes(totalDurations);

        Boolean isSlotsAvailable = isTimeSlotAvailable(salonDto, bookingStartTime, bookingEndTime);

        int totalPrice = serviceDto.stream()
                .mapToInt(ServiceOfferingDto::getPrice)
                .sum();

        Set<UUID> idList=serviceDto.stream()
                .map(ServiceOfferingDto::getServiceId)
                .collect(Collectors.toSet());

        Booking newbooking = new Booking();
        newbooking.setCustomerId((userDto.userId()));
        newbooking.setSalonId(salonDto.getSalonId());
        newbooking.setServiceIds(idList);
        newbooking.setStatus(BookingStatus.PENDING);
        newbooking.setStartTime(bookingStartTime);
        newbooking.setEndTime(bookingEndTime);
        newbooking.setTotal(totalPrice);
        return bookingRepo.save(newbooking);
    }

    @Override
    public List<Booking> getBookingByCustomer(UUID customerId){
        return bookingRepo.findByCustomerId(customerId);
    }

    @Override
    public List<Booking> getBookingBySalon(UUID salonId){
        return bookingRepo.findBySalonId(salonId);
    }

    @Override
    public Booking getBookingById(UUID bookingId) throws BookingNotFoundException {
        Optional<Booking> booking = bookingRepo.findById(bookingId);
        if (booking.isEmpty()) {
            throw new BookingNotFoundException("Booking not found with this id "+bookingId);
        }
        return booking.get();
    }

    @Override
    public Booking updateBooking(UUID bookingId, BookingStatus status) {
        Booking booking = getBookingById(bookingId);
        booking.setStatus(status);
        return bookingRepo.save(booking);
    }

    @Override
    public List<Booking> getBookingByDate(LocalDate date, UUID salonId) {
        List<Booking> allBookings=getBookingBySalon(salonId);
        if (date == null){
            return allBookings;
        }
        
        return allBookings.stream()
                .filter(booking -> isSameDate(booking.getStartTime(), date)
                || isSameDate(booking.getEndTime(), date)).toList();
    }

    private boolean isSameDate(LocalDateTime dateTime, LocalDate date) {
        return dateTime.toLocalDate().isEqual(date);
    }

    @Override
    public SalonReport getSalonReport(UUID salonId) throws SalonNotFoundException {
        List<Booking> bookings = getBookingBySalon(salonId);

        Double totalEarning = bookings.stream()
                .mapToDouble(Booking::getTotal).sum();

        Integer totalBookings = bookings.size();

        List<Booking> canceledBookings=bookings.stream()
                .filter(b -> b.getStatus().equals(BookingStatus.CANCELED))
                .toList();

        Double totalRefund=canceledBookings.stream()
                .mapToDouble(Booking::getTotal)
                .sum();

        SalonReport report=new SalonReport();
        report.setSalonId(salonId);
        report.setCancelBookings(canceledBookings.size());
        report.setTotalBookings(totalBookings);
        report.setTotalEarnings(totalEarning);
        report.setTotalRefund(totalRefund);
//        report.setSalonName(bookings.get());

        return report;
    }
}
