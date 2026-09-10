package com.EventHive.realtime.Entity;

import com.EventHive.realtime.Enum.SeatType;

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
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name="seat")
@Getter
@Setter
public class Seat {
    @Id
    @Column(name="seat_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long  seatId;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="venue_id")
    private Venue venue;
    private String rowLabel;
    private int seatNumber;
    private String section;
    @Enumerated(EnumType.STRING)
    private SeatType seatType;
}
