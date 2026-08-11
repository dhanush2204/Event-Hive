package com.EventHive.realtime.JpaRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EventHive.realtime.Entity.Booking;
@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer>{
    List<Booking> findByUserId(int user_id);
}
