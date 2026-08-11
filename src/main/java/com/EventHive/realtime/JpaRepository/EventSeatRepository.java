package com.EventHive.realtime.JpaRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EventHive.realtime.Entity.EventSeat;

@Repository
public interface EventSeatRepository extends JpaRepository<EventSeat, Integer>{
    List<EventSeat> findByEventId(int event_id);                          // all seats for an event (this covers "seat availability by eventId")
    List<EventSeat> findByEventIdAndStatus(int event_id, String status);  // filtered availability, e.g. status = "AVAILABLE"
    Optional<EventSeat> findByEventIdAndSeatId(int event_id,int seat_id);
}
