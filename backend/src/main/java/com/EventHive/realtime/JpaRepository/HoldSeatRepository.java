package com.EventHive.realtime.JpaRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.EventHive.realtime.Entity.HoldSeat;

public interface HoldSeatRepository extends JpaRepository<HoldSeat,Long>{
    List<HoldSeat> findByHold_HoldId(Long holdId);
}
