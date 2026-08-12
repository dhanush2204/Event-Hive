package com.EventHive.realtime.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EventHive.realtime.Entity.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer>{
    List<Event> findByVenueId(Long venueId);
    List<Event> findByEventDateAfter(LocalDateTime EventDate);
}
