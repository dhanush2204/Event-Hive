package com.EventHive.realtime.JpaRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EventHive.realtime.Entity.EventSeat;

@Repository
public interface EventSeatRepository extends JpaRepository<EventSeat, Long>{
    List<EventSeat> findByEvent_EventId(Long eventId);

    List<EventSeat> findByEvent_EventIdAndStatus(Long eventId, String status);

    Optional<EventSeat> findByEvent_EventIdAndSeat_SeatId(Long eventId, Long seatId);
}
