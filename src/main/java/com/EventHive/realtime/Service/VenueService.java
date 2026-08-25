package com.EventHive.realtime.Service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EventHive.realtime.DTO.VenueRequestDTO;
import com.EventHive.realtime.DTO.VenueResponseDTO;
import com.EventHive.realtime.Entity.Venue;
import com.EventHive.realtime.Exception.VenueNotFoundException;
import com.EventHive.realtime.JpaRepository.VenueRepository;

@Service
public class VenueService {
    @Autowired
    VenueRepository venueRepo;
    public VenueResponseDTO convertToResponseDTO(Venue venue){
        VenueResponseDTO dto=new VenueResponseDTO();
        dto.setVenueId(venue.getVenueId());
        dto.setVenueName(venue.getVenueName());
        dto.setVenueAddress(venue.getVenueAddress());
        dto.setVenueCapacity(venue.getVenueCapacity());
        return dto;
    }
    public VenueResponseDTO createVenue(VenueRequestDTO request){
        Venue venue=new Venue();
        venue.setVenueName(request.getVenueName());
        venue.setVenueAddress(request.getVenueAddress());
        venue.setVenueCapacity(request.getVenueCapacity());
        venue.setCreatedAt(LocalDateTime.now());
        venueRepo.save(venue);
        return convertToResponseDTO(venue);
    }
    public VenueResponseDTO getVenueById(Long venueId){
        Venue venue=venueRepo.findById(venueId)
                .orElseThrow(()->new VenueNotFoundException("Venue with venueId "+venueId+" doesn't exist"));
        return convertToResponseDTO(venue);
    }
}
