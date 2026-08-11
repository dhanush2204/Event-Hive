package com.EventHive.realtime.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EventHive.realtime.Entity.Venue;

@Repository
public interface VenueRepository extends JpaRepository<Venue, Integer>{

}
