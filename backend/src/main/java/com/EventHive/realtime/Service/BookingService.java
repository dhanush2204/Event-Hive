package com.EventHive.realtime.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.EventHive.realtime.DTO.BookingResponseDTO;
import com.EventHive.realtime.Entity.Booking;
import com.EventHive.realtime.Entity.BookingSeat;
import com.EventHive.realtime.Entity.Hold;
import com.EventHive.realtime.Entity.HoldSeat;
import com.EventHive.realtime.Entity.Payment;
import com.EventHive.realtime.Entity.Seat;
import com.EventHive.realtime.Enum.BookingStatus;
import com.EventHive.realtime.Enum.HoldStatus;
import com.EventHive.realtime.Enum.PaymentStatus;
import com.EventHive.realtime.Exception.BookingNotAllowedException;
import com.EventHive.realtime.JpaRepository.BookingRepository;
import com.EventHive.realtime.JpaRepository.BookingSeatRepository;
import com.EventHive.realtime.JpaRepository.EventSeatRepository;
import com.EventHive.realtime.JpaRepository.HoldSeatRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepo;
    private final BookingSeatRepository bookingSeatRepo;
    private final HoldSeatRepository holdSeatRepo;
    private final EventSeatRepository eventSeatRepo;

    public BookingResponseDTO convertToResponseDTO(Booking booking){
        BookingResponseDTO response=new BookingResponseDTO();
        response.setBookingId(booking.getBookingId());
        response.setEventId(booking.getEvent().getEventId());
        response.setEventName(booking.getEvent().getEventName());
        response.setBookingStatus(booking.getBookingStatus());
        response.setBookedAt(booking.getCreatedAt());
        List<BookingSeat> bookingSeats=bookingSeatRepo.findByBookingId(booking.getBookingId());
        response.setTotalAmount(booking.getTotalAmount());
        List<String> seats=bookingSeats.stream()
                         .map(bookingSeat ->{
                            Seat seat=bookingSeat.getEventSeat().getSeat();
                            return seat.getRowLabel() + seat.getSeatNumber();
                         })
                         .toList();
        response.setSeats(seats);

        return response;                 

    }
    @Transactional 
    public BookingResponseDTO completeBooking(Payment payment){
        if(payment.getStatus()!=PaymentStatus.SUCCESS){
            throw new RuntimeException("payment is not successful");
        }
        Hold hold=payment.getHold();

        Optional<Booking> existingBooking=bookingRepo.findByHold_HoldId(hold.getHoldId());

        if(existingBooking.isPresent()){
            return convertToResponseDTO(existingBooking.get());
        }
        LocalDateTime now=LocalDateTime.now();
        
        if(hold.getStatus()!=HoldStatus.ACTIVE){
            throw new BookingNotAllowedException("Hold is no longer active");
        }
        if(!hold.getExpiresAt().isAfter(now)){
            throw new BookingNotAllowedException("Hold time has expired");
        }
        List<HoldSeat> holdSeats=holdSeatRepo.findByHold_HoldId(hold.getHoldId());

        if(holdSeats.isEmpty()){
            throw new RuntimeException("No seats found for this hold");
        }

        List<Long> eventSeats=holdSeats.stream()
                        .map(holdSeat->
                            holdSeat.getEventSeat().getEventseatId()
                        )
                        .toList();
        int updatedCount=eventSeatRepo.convertHeldSeatsToBooked(eventSeats, hold.getHoldId(), now);
        if(updatedCount!=eventSeats.size()){
            throw new BookingNotAllowedException("Some seats could not be converted to BOOKED");    
        }
        Booking booking=new Booking();
        booking.setUser(hold.getUser());
        booking.setEvent(hold.getEvent());
        booking.setBookingStatus(BookingStatus.CANCELLED);
        booking.setHold(hold);
        booking.setTotalAmount(payment.getAmount());
        booking.setCreatedAt(now);
        
        Booking savedBooking=bookingRepo.save(booking);
        List<BookingSeat> bookingSeats = holdSeats.stream()
            .map(holdSeat -> {

                BookingSeat bookingSeat = new BookingSeat();

                bookingSeat.setBooking(savedBooking);
                bookingSeat.setEventSeat(
                        holdSeat.getEventSeat()
                );

                return bookingSeat;
            })
            .toList();

        bookingSeatRepo.saveAll(bookingSeats);
        
        hold.setStatus(HoldStatus.CONVERTED);

    return convertToResponseDTO(booking);
    }
}
