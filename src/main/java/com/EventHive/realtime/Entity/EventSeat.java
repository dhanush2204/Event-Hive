package com.EventHive.realtime.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="event_seats", uniqueConstraints=@UniqueConstraint(columnNames={"event_id","seat_id"}))
public class EventSeat {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="eventseat_id")
    private int eventseat_id;
    @ManyToOne
    @JoinColumn(name="event_id")
    private Event event;
    @ManyToOne
    @JoinColumn(name="seat_id")
    private Seat seat;
    private int price;
    private String status;
    private String hold_expires_at;
}
