package com.EventHive.realtime.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EventHive.realtime.DTO.HoldRequestDTO;
import com.EventHive.realtime.DTO.HoldResponseDTO;
import com.EventHive.realtime.Entity.Event;
import com.EventHive.realtime.Entity.EventSeat;
import com.EventHive.realtime.Entity.User;
import com.EventHive.realtime.Enum.EventSeatStatus;
import com.EventHive.realtime.Enum.UserRole;
import com.EventHive.realtime.Exception.BookingNotAllowedException;
import com.EventHive.realtime.Exception.EventNotFoundException;
import com.EventHive.realtime.Exception.HoldNotAllowedException;
import com.EventHive.realtime.Exception.UserNotFoundException;
import com.EventHive.realtime.JpaRepository.EventRepository;
import com.EventHive.realtime.JpaRepository.EventSeatRepository;
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
    @Transactional
    public HoldResponseDTO createHold(HoldRequestDTO request){
        User user = userRepo.findById(request.getUserId())
                      .orElseThrow(()->new UserNotFoundException("User not found with id "+request.getUserId()));
        if(user.getRole()==UserRole.ADMIN){
            throw new RuntimeException("The user is not eligible to make this request");
        }              
        Event event = eventRepo.findById(request.getEventId())
                      .orElseThrow(() -> new EventNotFoundException("Event not found with id: " + request.getEventId()));
        LocalDateTime now=LocalDateTime.now();
        LocalDateTime bookingCutoff=event.getEventDate().plusMinutes(30);
        if(now.isAfter(bookingCutoff)){
            throw new BookingNotAllowedException("Booking is not allowed at this moment");
        }
        List<EventSeat> eventSeats=eventSeatRepo.findAllById(request.getEventSeatIds());
        if(eventSeats.size()!=request.getEventSeatIds().size()){
            throw new RuntimeException("One or more seats are not valid");
        }
        for(EventSeat eventSeat:eventSeats){
            if(eventSeat.getEvent().getEventId()!=event.getEventId()){
                throw new RuntimeException("there's a mismatch between seat selection and event");
            }
        }
        for(EventSeat eventSeat:eventSeats){
            if(eventSeat.getStatus()==EventSeatStatus.BOOKED){
                throw new HoldNotAllowedException("Cannot process the hold request");
            }
            else if(eventSeat.getStatus()==EventSeatStatus.HELD && eventSeat.getHoldExpiresAt().isAfter(now)){
                throw new HoldNotAllowedException("Cannot process the hold request");
            }
        }

    }
}
