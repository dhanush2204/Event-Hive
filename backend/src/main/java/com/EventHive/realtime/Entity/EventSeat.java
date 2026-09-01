package com.EventHive.realtime.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="event_seat", uniqueConstraints=@UniqueConstraint(columnNames={"event_id","seat_id"}))
public class EventSeat {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="eventseat_id")
    private Long eventseatId;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="event_id")
    private Event event;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="seat_id")
    private Seat seat;
    private int price;
    private String status;
    private LocalDateTime holdExpiresAt;
}
