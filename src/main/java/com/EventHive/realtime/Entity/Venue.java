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
    private int  venue_id;
    private String venue_name;
    private String venue_address;
    private int venue_capacity;
    private LocalDate created_at;
    @OneToMany(mappedBy="venue")
    private List<Seat> seats;
    @OneToMany(mappedBy="venue")
    private List<Event> events;
}
