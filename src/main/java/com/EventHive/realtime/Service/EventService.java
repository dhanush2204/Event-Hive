package com.EventHive.realtime.Service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EventHive.realtime.DTO.EventRequestDTO;
import com.EventHive.realtime.DTO.EventResponseDTO;
import com.EventHive.realtime.Entity.Event;
import com.EventHive.realtime.Entity.Venue;
import com.EventHive.realtime.Enum.EventStatus;
import com.EventHive.realtime.Exception.EventNotFoundException;
import com.EventHive.realtime.JpaRepository.EventRepository;
import com.EventHive.realtime.JpaRepository.VenueRepository;

@Service
public class EventService {
    @Autowired
    EventRepository eventRepo;
    @Autowired
    VenueRepository venueRepo;
    @Autowired
    VenueService venueService;
    public EventResponseDTO convertToResponseDTO(Event event){
        EventResponseDTO dto=new EventResponseDTO();
        dto.setEventId(event.getEventId());
        dto.setEventName(event.getEventName());
        dto.setDescription(event.getDescription());
        dto.setGenre(event.getGenre());
        dto.setVenue(venueService.convertToResponseDTO(event.getVenue()));
        dto.setEventDate(event.getEventDate());
        dto.setEndDateTime(event.getEndDateTime());
        dto.setStatus(event.getStatus());
        dto.setCreatedAt(event.getCreatedAt());
        return dto;
    }
    public EventResponseDTO createEvent(EventRequestDTO request){
        if(LocalDateTime.now().isAfter(request.getEventDate())){
            throw new RuntimeException(request.getEventDate()+" should be in future");
        }
        if(request.getEventDate().isAfter(request.getEndDateTime()) || request.getEventDate().isEqual(request.getEndDateTime())){
            throw new RuntimeException("EndDateTime should be in future with respect to startDateTime");
        }
        Venue venue=venueRepo.findById(request.getVenueId())
                .orElseThrow(()->new RuntimeException
                ("this venue with id "+request.getVenueId()+" does not exist"));
        Event event=new Event();
        event.setEventName(request.getEventName());
        event.setDescription(request.getDescription());
        event.setGenre(request.getGenre());
        event.setVenue(venue);
        event.setStatus(EventStatus.UPCOMING);
        event.setEventDate(request.getEventDate());
        event.setEndDateTime(request.getEndDateTime());
        event.setCreatedAt(LocalDateTime.now());
        eventRepo.save(event);
        return convertToResponseDTO(event);
    }
    public EventResponseDTO getEventById(Long eventId){
        Event event=eventRepo.findById(eventId)
            .orElseThrow(()->new EventNotFoundException("event with eventId "+eventId+" doesn't exist"));
        return convertToResponseDTO(event);
    }
}
