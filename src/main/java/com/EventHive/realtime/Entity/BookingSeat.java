package com.EventHive.realtime.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

@Entity
public class BookingSeat {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int BookingSeat_id;
    @JoinColumn(name="booking_id")
    private Booking booking_id;
    @JoinColumn(name="eventseat_id")
    private EventSeat eventseat_id;
}
