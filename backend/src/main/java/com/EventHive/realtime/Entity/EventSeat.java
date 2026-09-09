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
    @Enumerated(EnumType.STRING)
    private EventSeatStatus status;
    private LocalDateTime holdExpiresAt;

    public Long getEventseatId() {
        return eventseatId;
    }

    public void setEventseatId(Long eventseatId) {
        this.eventseatId = eventseatId;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public EventSeatStatus getEventSeatStatus() {
        return status;
    }

    public void setStatus(EventSeatStatus status) {
        this.status = status;
    }

    public LocalDateTime getHoldExpiresAt() {
        return holdExpiresAt;
    }

    public void setHoldExpiresAt(LocalDateTime holdExpiresAt) {
        this.holdExpiresAt = holdExpiresAt;
    }

}
