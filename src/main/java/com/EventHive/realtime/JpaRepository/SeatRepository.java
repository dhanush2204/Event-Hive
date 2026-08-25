package com.EventHive.realtime.JpaRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EventHive.realtime.Entity.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long>{
    List<Seat> findByVenue_VenueId(Long venueId);

}
