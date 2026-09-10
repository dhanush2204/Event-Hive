package com.EventHive.realtime.DTO;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class HoldRequestDTO {
    @NotNull(message = "userId is Required")
    @Positive(message = "userId must be Positive")
    private Long userId;

    @NotNull(message = "eventId is Required")
    @Positive(message = "eventId must be Positive")
    private Long eventId;

    @NotEmpty(message = "At least one event seat is required")
    private List<@NotNull @Positive Long> eventSeatIds;
}
