package com.EventHive.realtime.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EventHive.realtime.DTO.BookingRequestDTO;
import com.EventHive.realtime.DTO.HoldResponseDTO;
import com.EventHive.realtime.Entity.Event;
import com.EventHive.realtime.Entity.EventSeat;
import com.EventHive.realtime.Entity.Hold;
import com.EventHive.realtime.Entity.HoldSeat;
import com.EventHive.realtime.Entity.User;
import com.EventHive.realtime.Enum.EventSeatStatus;
import com.EventHive.realtime.Enum.HoldStatus;
import com.EventHive.realtime.Enum.UserRole;
import com.EventHive.realtime.Exception.BookingNotAllowedException;
import com.EventHive.realtime.Exception.EventNotFoundException;
import com.EventHive.realtime.Exception.HoldNotAllowedException;
import com.EventHive.realtime.Exception.UserNotFoundException;
import com.EventHive.realtime.JpaRepository.EventRepository;
import com.EventHive.realtime.JpaRepository.EventSeatRepository;
import com.EventHive.realtime.JpaRepository.HoldRepository;
import com.EventHive.realtime.JpaRepository.HoldSeatRepository;
import com.EventHive.realtime.JpaRepository.UserRepository;

import jakarta.transaction.Transactional;
import jdk.jshell.spi.ExecutionControl;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class HoldService {
    UserRepository userRepo;
    EventRepository eventRepo;
    EventSeatRepository eventSeatRepo;
    HoldSeatRepository holdSeatRepo;
    HoldRepository holdRepo;
    @Transactional
    public Hold createHold(BookingRequestDTO request){
        User user = userRepo.findById(request.getUserId())
                      .orElseThrow(()->new UserNotFoundException("User not found with id "+request.getUserId()));
        if(user.getRole()!=UserRole.CUSTOMER){
            throw new RuntimeException("The user is not eligible to make this request");
        }              
        Event event = eventRepo.findById(request.getEventId())
                      .orElseThrow(() -> new EventNotFoundException("Event not found with id: " + request.getEventId()));
        LocalDateTime now=LocalDateTime.now();
        LocalDateTime bookingCutoff=event.getEventDate().plusMinutes(30);
        if(now.isAfter(bookingCutoff)){
            throw new BookingNotAllowedException("Booking is not allowed at this moment");
        }
        if(new HashSet<>(request.getEventSeatIds()).size()!=request.getEventSeatIds().size()){
            throw new HoldNotAllowedException("Duplicate event seat IDs are not allowed");
        }
        if(request.getEventSeatIds().size()>12){
            throw new HoldNotAllowedException("A maximum of 12 seats can be held at once");
        }
        List<EventSeat> eventSeats=eventSeatRepo.findAllById(request.getEventSeatIds());
        if(eventSeats.size()!=request.getEventSeatIds().size()){
            throw new HoldNotAllowedException("One or more seats are not valid");
        }
        for(EventSeat eventSeat:eventSeats){
            if(!eventSeat.getEvent().getEventId().equals(event.getEventId())){
                throw new HoldNotAllowedException("One or more seats do not belong to this event");
            }
        }
        LocalDateTime expiresAt=now.plusMinutes(10);
        
        int updated=eventSeatRepo.tryHoldSeats(event.getEventId(),request.getEventSeatIds(), expiresAt, now);

        if(updated!=request.getEventSeatIds().size()){
            throw new HoldNotAllowedException("One or more requested seats are no longer available");
        }

        Hold hold=new Hold();
        hold.setUser(user);
        hold.setEvent(event);
        hold.setStatus(HoldStatus.ACTIVE);
        hold.setCreatedAt(now);
        hold.setExpiresAt(expiresAt);

        holdRepo.save(hold);
        
        List<HoldSeat> holdSeats=new ArrayList<>();
        for(EventSeat eventSeat :eventSeats){
            HoldSeat holdSeat=new HoldSeat();
            holdSeat.setHold(hold);
            holdSeat.setEventSeat(eventSeat);
            holdSeats.add(holdSeat);
        }
        
        holdSeatRepo.saveAll(holdSeats);

        return hold;
    }
}
