package com.EventHive.realtime.DTO;

import java.time.LocalDateTime;

import com.EventHive.realtime.Enum.EventGenre;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotBlank;

@JsonIgnoreProperties(ignoreUnknown = false)
public class EventUpdateRequestDTO {
    private String eventName;
    private String description;
    private EventGenre genre;
    private LocalDateTime eventDate;
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
    public LocalDateTime getEventDate() {
        return eventDate;
    }
    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }
}
