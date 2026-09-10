package com.EventHive.realtime.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeatDetailsResponseDTO {
    private Long seatId;
    private String rowLabel;
    private int seatNumber;
    private String section;
    private String seatType;
}
