package com.EventHive.realtime.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.EventHive.realtime.Entity.EventSeat;

@Repository
public interface EventSeatRepository extends JpaRepository<EventSeat, Long>{
    List<EventSeat> findByEvent_EventId(Long eventId);

    List<EventSeat> findByEvent_EventIdAndStatus(Long eventId, String status);

    Optional<EventSeat> findByEvent_EventIdAndSeat_SeatId(Long eventId, Long seatId);

    @Modifying
    @Query("""
        Update EventSeat as es
        set es.status='HELD',
            es.holdExpiresAt=:expiresAt,
        where es.event.eventId=:eventId,
        AND es.eventseatId IN :eventSeatIds
      AND (
          es.status = 'AVAILABLE'
          OR (
              es.status = 'HELD'
              AND es.holdExpiresAt <= :now
          )
      )          
 """ )
    int tryHoldSeats(
        @Param("eventId") Long eventId,
        @Param("eventSeatIds") List<Long> eventSeatIds,
        @Param("expiresAt") LocalDateTime expiresAt,
        @Param("now") LocalDateTime now
    );
}
