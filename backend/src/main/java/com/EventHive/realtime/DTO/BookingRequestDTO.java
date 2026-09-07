
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class BookingRequestDTO {

    @NotNull(message = "userId is Required")
    @Positive(message = "userId must be Positive")
    private Long userId;

    @NotNull(message = "eventId is Required")
    @Positive(message = "eventId must be Positive")
    private Long eventId;

    @NotEmpty(message = "At least one event seat is required")
    private List<Long> eventSeatIds;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public List<Long> getEventSeatIds() {
        return eventSeatIds;
    }

    public void setEventSeatIds(List<Long> eventSeatIds) {
        this.eventSeatIds = eventSeatIds;
    }
}