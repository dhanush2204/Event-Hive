package com.EventHive.realtime.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.EventHive.realtime.Entity.Event;
import com.EventHive.realtime.Enum.EventStatus;
import static com.EventHive.realtime.Enum.EventStatus.ONGOING;
import static com.EventHive.realtime.Enum.EventStatus.UPCOMING;
import com.EventHive.realtime.JpaRepository.EventRepository;

import jakarta.transaction.Transactional;

@Component
public class EventStatusScheduler {
    private final EventRepository eventRepository;
    @Autowired
    public EventStatusScheduler(EventRepository eventRepository){
        this.eventRepository=eventRepository;
    }
    @Scheduled(fixedDelay=100000)
    @Transactional
    public void updateEventStatuses(){
        LocalDateTime now=LocalDateTime.now();
        List<Event> upcomingEvents=eventRepository.findByStatusAndEventDateLessThanEqual(UPCOMING, now);
        for(Event event:upcomingEvents){
            event.setStatus(EventStatus.ONGOING);
        }
        eventRepository.saveAll(upcomingEvents);

        List<Event> ongoingEvents=eventRepository.findByStatusAndEndDateTimeLessThanEqual(ONGOING,now);
        for(Event event:ongoingEvents){
            event.setStatus(EventStatus.COMPLETED);
        }
        eventRepository.saveAll(ongoingEvents);
    }
}
