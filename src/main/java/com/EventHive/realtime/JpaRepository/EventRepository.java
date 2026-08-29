package com.EventHive.realtime.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EventHive.realtime.Entity.Event;
import com.EventHive.realtime.Enum.EventStatus;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>{
    List<Event> findByVenue_VenueId(Long venueId);
    List<Event> findByEventDateAfter(LocalDateTime eventDate);
    List<Event> findByStatusAndEventDateLessThanEqual(EventStatus status,LocalDateTime now);
    List<Event> findByStatusAndEndDateTimeLessThanEqual(EventStatus status,LocalDateTime now);
    List<Event> findByStatus(EventStatus status);
    List<Event> findByStatusAndEventDateGreaterThanEqual(EventStatus status, LocalDateTime cutOffTime);
}
