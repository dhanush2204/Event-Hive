package com.EventHive.realtime.DTO;

import java.time.LocalDateTime;

import com.EventHive.realtime.Enum.EventGenre;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class EventRequestDTO {
    @NotBlank(message="Name is Required")
    private String eventName;
    @NotBlank(message="Description is required")
    private String description;
    @NotNull(message="Genre is required")
    private EventGenre genre;
    @Positive(message="venueId must be Positive")
    @NotNull(message="venueId is Required")
    private Long venueId;
    @NotNull
    private LocalDateTime eventDate;
    @NotNull
    private LocalDateTime endDateTime;

    public String getEventName() {
        return eventName;
    }

    public String getDescription() {
        return description;
    }

    public EventGenre getGenre() {
        return genre;
    }

    public long getVenueId() {
        return venueId;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
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

    public void setVenueId(long venueId) {
        this.venueId = venueId;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

}
