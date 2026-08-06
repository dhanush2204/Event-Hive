package com.EventHive.realtime.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;

@Entity
public class BookingSeat {
    private String BookingSeat_id;
    @JoinColumn(name="booking_id")
    private Booking booking_id;
    @JoinColumn(name="eventseat_id")
    private EventSeat eventseat_id;
}
