package com.EventHive.realtime.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EventHive.realtime.Entity.Hold;

@Repository
public interface HoldRepository extends JpaRepository<Hold,Long>{

}
