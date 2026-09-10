package com.EventHive.realtime.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
    name = "hold_seat",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {"hold_id", "event_seat_id"}
    )
)
public class HoldSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long holdSeatId;

    @ManyToOne
    @JoinColumn(name = "hold_id", nullable = false)
    private Hold hold;

    @ManyToOne
    @JoinColumn(name = "event_seat_id", nullable = false)
    private EventSeat eventSeat;
}