package com.EventHive.realtime.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Seat")
public class Seat {
    @Id
    @Column(name="seat_id")
    private String seat_id;
    @ManyToOne
    @JoinColumn(name="venue_id")
    private Venue venue;
    private String row_label;
    private int seat_number;
    private String section;
    private String Seat_type;
}
