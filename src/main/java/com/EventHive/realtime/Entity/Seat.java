package com.EventHive.realtime.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="seat")
public class Seat {
    @Id
    @Column(name="seat_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long  seatId;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="venue_id")
    private Venue venue;
    private String rowLabel;
    private String seatNumber;
    private String section;
    private String SeatType;
}
