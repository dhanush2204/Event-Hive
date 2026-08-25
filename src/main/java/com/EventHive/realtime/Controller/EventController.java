package com.EventHive.realtime.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EventHive.realtime.DTO.EventRequestDTO;
import com.EventHive.realtime.DTO.EventResponseDTO;
import com.EventHive.realtime.Service.EventService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    EventService eventservice;
    @PostMapping
    public EventResponseDTO createEvent(@Valid @RequestBody EventRequestDTO request){
        return eventservice.createEvent(request);
    }
    @GetMapping("/{eventId}")
    public EventResponseDTO getEventById(@PathVariable Long eventId){
        return eventservice.getEventById(eventId);
    }
}
