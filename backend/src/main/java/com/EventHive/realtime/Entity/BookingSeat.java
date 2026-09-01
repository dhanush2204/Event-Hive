package com.EventHive.realtime.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="booking_seat")
public class BookingSeat {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long BookingSeatId;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="booking_id")
    private Booking bookingId;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="eventseat_id")
    private EventSeat eventseatId;
}
