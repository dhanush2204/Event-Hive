package com.EventHive.realtime.DTO;
import java.time.LocalDateTime;
import java.util.List;

import com.EventHive.realtime.Enum.BookingStatus;

import lombok.Getter;
import lombok.Setter;
@Getter 
@Setter 
public class BookingResponseDTO {

    private Long bookingId;
    private Long eventId;
    private String eventName;
    private Integer totalAmount;
    private List<String> seats;
    private BookingStatus bookingStatus;
    private LocalDateTime bookedAt;

}
