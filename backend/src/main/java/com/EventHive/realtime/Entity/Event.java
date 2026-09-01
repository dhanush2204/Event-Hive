package com.EventHive.realtime.Entity;

import java.time.LocalDateTime;
import java.util.List;

import com.EventHive.realtime.Enum.EventGenre;
import com.EventHive.realtime.Enum.EventStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    private String eventName;
    private String description;
    @Enumerated(EnumType.STRING)
    private EventGenre genre;
    private LocalDateTime eventDate;
    private LocalDateTime endDateTime;
    @Enumerated(EnumType.STRING)
    private EventStatus status;
    private LocalDateTime createdAt;
    @OneToMany(mappedBy="event")
    private List<EventSeat> eventseats;
    @OneToMany(mappedBy="event")
    private List<Booking> bookings;

    public Long getEventId() {
        return eventId;
    }

    public Venue getVenue() {
        return venue;
    }

    public String getEventName() {
        return eventName;
    }

    public String getDescription() {
        return description;
    }

    public EventGenre getGenre() {
        return genre;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public EventStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<EventSeat> getEventseats() {
        return eventseats;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGenre(EventGenre genre) {
        this.genre = genre;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
}
