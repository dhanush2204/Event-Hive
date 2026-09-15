package com.EventHive.realtime.JpaRepository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.EventHive.realtime.Entity.Hold;

@Repository
public interface HoldRepository extends JpaRepository<Hold,Long>{
    @Modifying
    @Query("""
        update Hold as h
        set h.status='EXPIRED'
        where h.status='ACTIVE'
        and h.expiresAt<=:now   
       """ )
    int expireHolds(@Param("now") LocalDateTime now);
}
