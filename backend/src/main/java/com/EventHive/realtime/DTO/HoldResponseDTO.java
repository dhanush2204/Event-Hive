package com.EventHive.realtime.DTO;

import java.time.LocalDateTime;
import java.util.List;

import com.EventHive.realtime.Enum.HoldStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HoldResponseDTO {
    private Long holdId;

    private Long userId;

    private Long eventId;

    private String eventName;

    private List<Long> eventSeatIds;

    private HoldStatus status;

    private LocalDateTime expiresAt;

    private LocalDateTime createdAt;
}
