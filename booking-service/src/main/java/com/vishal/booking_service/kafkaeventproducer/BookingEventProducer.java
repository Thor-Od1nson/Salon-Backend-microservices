package com.vishal.booking_service.kafkaeventproducer;

import com.vishal.booking_service.kafkaevent.BookingEvent;
import com.vishal.booking_service.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class BookingEventProducer {

    @Autowired
    private KafkaTemplate<String, BookingEvent> kafkaTemplate;

    public void sendBookingCreatedEvent(Booking booking, String userName, String salonName) {
        BookingEvent event = new BookingEvent();

        event.setBookingId(booking.getBookingId());
        event.setFullName(userName);
        event.setSalonName(salonName);
        event.setStatus(booking.getStatus());
        event.setStartTime(booking.getStartTime());
        event.setEndTime(booking.getEndTime());
        event.setMessage("Your appointment is booked with this "+salonName+" salon.");

        kafkaTemplate.send("booking.created", event);
    }

    public void sendBookingStatusChangedEvent(Booking booking, String userName, String salonName) {
        BookingEvent event =new BookingEvent();
        event.setBookingId(booking.getBookingId());
        event.setFullName(userName);
        event.setSalonName(salonName);
        event.setStatus(booking.getStatus());
        event.setStartTime(booking.getStartTime());
        event.setEndTime(booking.getEndTime());
        event.setMessage("your Booking Status is changed to "+booking.getStatus());

        kafkaTemplate.send("booking.status.updated", event);
    }
}
