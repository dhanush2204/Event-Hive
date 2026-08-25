package com.EventHive.realtime.Entity;

import java.time.LocalDateTime;
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
    private LocalDateTime createdAt;
    @OneToMany(mappedBy="venue")
    private List<Seat> seats;
    @OneToMany(mappedBy="venue")
    private List<Event> events;

    public Long getVenueId() {
        return venueId;
    }

    public String getVenueName() {
        return venueName;
    }

    public String getVenueAddress() {
        return venueAddress;
    }

    public int getVenueCapacity() {
        return venueCapacity;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public void setVenueAddress(String venueAddress) {
        this.venueAddress = venueAddress;
    }

    public void setVenueCapacity(int venueCapacity) {
        this.venueCapacity = venueCapacity;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
