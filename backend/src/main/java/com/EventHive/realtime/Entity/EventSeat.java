package com.EventHive.realtime.Entity;

import java.time.LocalDateTime;

import com.EventHive.realtime.Enum.EventSeatStatus;

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
import jakarta.persistence.UniqueConstraint;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name="event_seat", uniqueConstraints=@UniqueConstraint(columnNames={"event_id","seat_id"}))
public class EventSeat {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="eventseat_id")
    private Long eventseatId;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="event_id", nullable = false)
    private Event event;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="seat_id", nullable = false)
    private Seat seat;
    @Column(nullable = false)
    private int price;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventSeatStatus status;
    private LocalDateTime holdExpiresAt;
}
