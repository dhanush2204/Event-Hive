package com.EventHive.realtime.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EventHive.realtime.DTO.SeatDetailsResponseDTO;
import com.EventHive.realtime.DTO.SeatRequestDTO;
import com.EventHive.realtime.DTO.SeatResponseDTO;
import com.EventHive.realtime.Service.SeatService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/venues")
public class SeatController {
    private final SeatService seatService;
    @Autowired
    public SeatController(SeatService seatService){
        this.seatService=seatService;
    }
    @PostMapping("/{venueId}/seats")
    public ResponseEntity<SeatResponseDTO> createSeats(@PathVariable Long venueId, @Valid @RequestBody SeatRequestDTO request){
        SeatResponseDTO response=seatService.createSeats(venueId,request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/{venueId}/seats")
    public ResponseEntity<List<SeatDetailsResponseDTO>> getSeatsByVenue(@PathVariable Long venueId){
        List<SeatDetailsResponseDTO> response=seatService.getSeatsByVenue(venueId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
