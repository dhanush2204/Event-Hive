
import java.util.List;

import com.EventHive.realtime.Entity.Event;
import com.EventHive.realtime.Entity.EventSeat;
import com.EventHive.realtime.Entity.User;
import com.EventHive.realtime.Enum.EventStatus;
import com.EventHive.realtime.Exception.BookingNotAllowedException;
import com.EventHive.realtime.Exception.EventNotFoundException;
import com.EventHive.realtime.Exception.InvalidEventStateException;
import com.EventHive.realtime.Exception.UserNotFoundException;
import com.EventHive.realtime.JpaRepository.BookingRepository;
import com.EventHive.realtime.JpaRepository.BookingSeatRepository;
import com.EventHive.realtime.JpaRepository.EventRepository;
import com.EventHive.realtime.JpaRepository.EventSeatRepository;
import com.EventHive.realtime.JpaRepository.UserRepository;

public class BookingService {
    private final BookingRepository bookingRepo;
    private final BookingSeatRepository bookingSeatRepo;
    private final UserRepository userRepo;
    private final EventRepository eventRepo;
    private final EventSeatRepository eventSeatRepo;

    public BookingService(
            BookingRepository bookingRepo,
            BookingSeatRepository bookingSeatRepo,
            UserRepository userRepo,
            EventRepository eventRepo,
            EventSeatRepository eventSeatRepo) {

        this.bookingRepo = bookingRepo;
        this.bookingSeatRepo = bookingSeatRepo;
        this.userRepo = userRepo;
        this.eventRepo = eventRepo;
        this.eventSeatRepo = eventSeatRepo;
    }
    public BookingResponseDTO createBooking(BookingRequestDTO request){
        User user=userRepo.findById(request.getUserId())
                      .orElseThrow(()-> new UserNotFoundException("User not found with id "+request.getUserId()));
        Event event = eventRepo.findById(request.getEventId())
                      .orElseThrow(() -> new EventNotFoundException("Event not found with id: " + request.getEventId()));
        if(event.getStatus()!=EventStatus.UPCOMING){
            throw new BookingNotAllowedException("Booking not allowed for this event ");
        }
        EventStatus status=event.getStatus();
        if(status!=EventStatus.UPCOMING){
            throw new InvalidEventStateException("Event with id "+event.getEventId()+" is not allowed for booking");
        }
        List<EventSeat> eventseats=eventSeatRepo.findAllById(request.getEventSeatIds());
        if(eventseats.size()!=request.getEventSeatIds().size()){
            throw new RuntimeException("One or more seats are not valid");
        }
        for(EventSeat eventSeat:eventseats){
            if(eventSeat.getEvent().getEventId()!=event.getEventId()){
                throw new RuntimeException("there's a mismatch between eventseat and event");
            }
        }
    }
}
