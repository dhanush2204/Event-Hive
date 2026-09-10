package com.EventHive.realtime.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EventHive.realtime.DTO.SeatDetailsResponseDTO;
import com.EventHive.realtime.DTO.SeatRequestDTO;
import com.EventHive.realtime.DTO.SeatResponseDTO;
import com.EventHive.realtime.Entity.Seat;
import com.EventHive.realtime.Entity.Venue;
import com.EventHive.realtime.Enum.SeatType;
import com.EventHive.realtime.Exception.VenueNotFoundException;
import com.EventHive.realtime.JpaRepository.SeatRepository;
import com.EventHive.realtime.JpaRepository.VenueRepository;

import jakarta.transaction.Transactional;
@Service
public class SeatService {
    private final VenueRepository venueRepo;
    private final SeatRepository seatRepo;
    @Autowired
    public SeatService(VenueRepository venueRepo,SeatRepository seatRepo){
        this.venueRepo=venueRepo;
        this.seatRepo=seatRepo;
    }
    @Transactional
    public SeatResponseDTO createSeats(Long venueId, SeatRequestDTO request){
        SeatResponseDTO responseDto=new SeatResponseDTO();
        Venue venue=venueRepo.findById(venueId)
                .orElseThrow(()->new VenueNotFoundException("this venue with id "+venueId+" does not exist"));
        if(seatRepo.existsByVenue_VenueId(venueId)){
            throw new RuntimeException("Venue already designed with desinated seats");
        }
        int row=request.getNumberOfRows();
        int seatsPerRow=request.getSeatsPerRow();
        int totalSeats=(row*seatsPerRow);
        if(totalSeats>venue.getVenueCapacity()){
            throw new RuntimeException("Venue cannot accomodate more seats than the available capacity");
        }
        List<Seat> seats=new ArrayList<>();
        for(int i=0;i<row;i++){
            char rowLabel = (char) ('A' + i);
            for(int j=1;j<=seatsPerRow;j++){
                Seat seat=new Seat();
                seat.setVenue(venue);
                seat.setSeatType(SeatType.REGULAR);
                seat.setRowLabel(String.valueOf(rowLabel));
                seat.setSeatNumber(j);
                seats.add(seat);
            }
        }
        seatRepo.saveAll(seats);
        responseDto.setVenueId(venueId);
        responseDto.setTotalSeats(totalSeats);
        return responseDto;
    }
    public List<SeatDetailsResponseDTO> getSeatsByVenue(Long venueId){
        Venue venue=venueRepo.findById(venueId)
                .orElseThrow(()->new VenueNotFoundException("this venue with id "+venueId+" does not exist"));
        List<Seat> seats=seatRepo.findByVenue_VenueId(venueId);
        List<SeatDetailsResponseDTO> responseList = new ArrayList<>();
        SeatDetailsResponseDTO dto = new SeatDetailsResponseDTO();
        for(Seat seat:seats){
            dto.setSeatId(seat.getSeatId());
            dto.setRowLabel(seat.getRowLabel());
            dto.setSeatNumber(seat.getSeatNumber());
            responseList.add(dto);
        }
        return responseList;       
    }
}
