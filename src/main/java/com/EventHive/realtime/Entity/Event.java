package com.EventHive.realtime.Entity;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="event")
public class Event {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="event_id")
    private Long eventId;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="venue_id")
    private Venue venue;
    private String description;
    private LocalDateTime EventDate;
    private LocalDateTime end_time;
    private String status;
    @OneToMany(mappedBy="event")
    private List<EventSeat> eventseats;
    @OneToMany(mappedBy="event")
    private List<Booking> bookings;
}
