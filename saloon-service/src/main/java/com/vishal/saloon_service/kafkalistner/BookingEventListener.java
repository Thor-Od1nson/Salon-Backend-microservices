package com.vishal.saloon_service.kafkalistner;

import com.vishal.saloon_service.kafkaevent.BookingEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class BookingEventListener {

//    @KafkaListener(topics = "booking.created", groupId = "user-service-group", containerFactory = "kafkaListenerContainerFactory")
//    public void handleBookingEvent(BookingEvent event) {
//        System.out.println("✅ Received booking event in user-service: ");
//        System.out.println("  Booking ID: " + event.getBookingId());
//        System.out.println("  User: " + event.getFullName());
//        System.out.println("  Salon: " + event.getSalonName());
//        System.out.println("  Status: " + event.getStatus());
//        System.out.println("  Message: " + event.getMessage());
//        // process the event (e.g., update user/salon info)
//    }
}
