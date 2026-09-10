package com.EventHive.realtime.DTO;

import java.time.LocalDateTime;

import com.EventHive.realtime.Enum.EventGenre;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
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
    @NotNull
    @Positive
    private Integer price;
}
