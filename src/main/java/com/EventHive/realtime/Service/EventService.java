package com.EventHive.realtime.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EventHive.realtime.DTO.EventRequestDTO;
import com.EventHive.realtime.DTO.EventResponseDTO;
import com.EventHive.realtime.DTO.EventUpdateRequestDTO;
import com.EventHive.realtime.Entity.Event;
import com.EventHive.realtime.Entity.Venue;
import com.EventHive.realtime.Enum.EventGenre;
import com.EventHive.realtime.Enum.EventStatus;
import com.EventHive.realtime.Exception.EventNotFoundException;
import com.EventHive.realtime.Exception.InvalidDateTimeException;
import com.EventHive.realtime.Exception.InvalidEventDataException;
import com.EventHive.realtime.Exception.InvalidEventStateException;
import com.EventHive.realtime.JpaRepository.EventRepository;
import com.EventHive.realtime.JpaRepository.VenueRepository;

import jakarta.transaction.Transactional;

@Service
public class EventService {
    private final EventRepository eventRepo;
    private final VenueRepository venueRepo;
    private final VenueService venueService;
    @Autowired
    public EventService(EventRepository eventRepo,VenueRepository venueRepo,VenueService venueService){
        this.eventRepo=eventRepo;
        this.venueRepo=venueRepo;
        this.venueService=venueService;
    }
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
    public List<EventResponseDTO> getEvents(){
        LocalDateTime cutOffTime=LocalDateTime.now().minusMinutes(30);
        List<Event> events=new ArrayList<>();
        List<EventResponseDTO> eventDTOs=new ArrayList<>();
        List<Event> upcomingEvents=eventRepo.findByStatus(EventStatus.UPCOMING);
        List<Event> ongoingEvents=eventRepo.findByStatusAndEventDateGreaterThanEqual(EventStatus.ONGOING, cutOffTime);
        events.addAll(upcomingEvents);
        events.addAll(ongoingEvents);
        events.sort(Comparator.comparing(Event::getEventDate));
        for(Event event:events){
            eventDTOs.add(convertToResponseDTO(event));
        }
        return eventDTOs;
    }
    public EventResponseDTO cancelEvent(Long eventId){
        Event event=eventRepo.findById(eventId)
            .orElseThrow(()->new EventNotFoundException("event with eventId "+eventId+" doesn't exist"));
        if(event.getStatus()==EventStatus.UPCOMING){
            event.setStatus(EventStatus.CANCELLED);
        }
        else{
            throw new InvalidEventStateException("Event with eventId "+eventId+" is not an upcoming event");
        }
        eventRepo.save(event);
        return convertToResponseDTO(event);
    }
    @Transactional
    public EventResponseDTO updateEvent(EventUpdateRequestDTO dto,Long eventId){
        Event event=eventRepo.findById(eventId)
            .orElseThrow(()->new EventNotFoundException("event with eventId "+eventId+" doesn't exist"));
        if(event.getStatus()==EventStatus.UPCOMING){
            LocalDateTime now=LocalDateTime.now();
            LocalDateTime newStart;
            if(dto.getEventDate()!=null){
                newStart=dto.getEventDate();
            }
            else{
                newStart=event.getEventDate();
            }
            LocalDateTime newEnd;
            if(dto.getEndDateTime()!=null){
                newEnd=dto.getEndDateTime();
            }
            else{
                newEnd=event.getEndDateTime();
            }
            if(!newStart.isAfter(now) || !newEnd.isAfter(newStart)){
                throw new InvalidDateTimeException("Invalid Date Logic");
            }
            else{
                event.setEventDate(newStart);
                event.setEndDateTime(newEnd);
            }
            if(dto.getEventName()!=null){
                if (dto.getEventName().isBlank()) {
                    throw new InvalidEventDataException("Event name cannot be blank");
                }
                event.setEventName(dto.getEventName());
            }
            if(dto.getDescription()!=null){
                if(dto.getDescription().isBlank()){
                    throw new InvalidEventDataException("Description cannot be blank");
                }
                event.setDescription(dto.getDescription());
            }
            if(dto.getGenre()!=null){
                event.setGenre(dto.getGenre());
            }
        }
        else if(event.getStatus()==EventStatus.ONGOING){
            if(dto.getEventName()!=null){
                if (dto.getEventName().isBlank()) {
                    throw new InvalidEventDataException("Event name cannot be blank");
                }
                event.setEventName(dto.getEventName());
            }
            if(dto.getDescription()!=null){
                if(dto.getDescription().isBlank()){
                    throw new InvalidEventDataException("Description cannot be blank");
                }
                event.setDescription(dto.getDescription());
            }
            if(dto.getGenre()!=null){
                event.setGenre(dto.getGenre());
            }
        } 
        else{
            throw new InvalidEventStateException("Event cannot be updated in its current state");

        }
        eventRepo.save(event);
        return convertToResponseDTO(event);
    }
}
