package com.EventHive.realtime.Entity;

import java.time.LocalDateTime;

import com.EventHive.realtime.Enum.BookingStatus;

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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="booking")
@Getter
@Setter
public class Booking {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="booking_id")
    private Long bookingId;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="event_id")
    private Event event;
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="hold_id", nullable=false, unique=true)
    private Hold hold;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus bookingStatus;
    @Column(nullable = false)
    private int totalAmount;
    @Column(nullable = false)
    private LocalDateTime createdAt;
}
