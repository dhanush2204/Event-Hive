package com.EventHive.realtime.DTO;

import java.time.LocalDateTime;

import com.EventHive.realtime.Enum.EventGenre;
import com.EventHive.realtime.Enum.EventStatus;

public class EventResponseDTO {
    public Long eventId;
    public String eventName;
    public String Description;
    public EventGenre genre;
    public VenueResponseDTO venue;
    public LocalDateTime eventDate;
    public LocalDateTime endDateTime;
    public EventStatus status;
    public LocalDateTime createdAt;

    public Long getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getDescription() {
        return Description;
    }

    public EventGenre getGenre() {
        return genre;
    }

    public VenueResponseDTO getVenue() {
        return venue;
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

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setGenre(EventGenre genre) {
        this.genre = genre;
    }

    public void setVenue(VenueResponseDTO venue) {
        this.venue = venue;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
