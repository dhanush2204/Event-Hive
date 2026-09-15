package com.EventHive.realtime.Service;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.EventHive.realtime.JpaRepository.HoldRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class HoldExpirationScheduler {
    private final HoldRepository holdRepo;

    @Scheduled(fixedRate=60000)
    @Transactional
    public void expireHolds(){
        LocalDateTime now=LocalDateTime.now();
        int expiredCount=holdRepo.expireHolds(now);
        System.out.println("Expired holds: " + expiredCount);
    }
}
