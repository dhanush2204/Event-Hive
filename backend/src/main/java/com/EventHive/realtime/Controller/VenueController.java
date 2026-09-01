package com.EventHive.realtime.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EventHive.realtime.DTO.VenueRequestDTO;
import com.EventHive.realtime.DTO.VenueResponseDTO;
import com.EventHive.realtime.Service.VenueService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/venues")
public class VenueController {
    
    private final VenueService venueService;
    @Autowired
    public VenueController(VenueService venueService){
        this.venueService=venueService;
    }
    @PostMapping()
    public VenueResponseDTO createVenue(@Valid @RequestBody VenueRequestDTO venuerequestDto){
        return venueService.createVenue(venuerequestDto);
    }
    @GetMapping("/{venueId}")
    public VenueResponseDTO getVenueById(@PathVariable Long venueId){
        return venueService.getVenueById(venueId);
    }
}
