package com.EventHive.realtime.Entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name="venue")
public class Venue {
    @Id
    @Column(name="venue_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long venueId;
    private String venueName;
    private String venueAddress;
    private int venueCapacity;
    private LocalDate createdAt;
    @OneToMany(mappedBy="venue")
    private List<Seat> seats;
    @OneToMany(mappedBy="venue")
    private List<Event> events;
}
