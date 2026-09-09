package com.EventHive.realtime.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class SeatRequestDTO {
    @NotNull(message="Number of rows cannot be null")
    @Positive(message="Number of rows must be positive")
    private Integer numberOfRows;
    @NotNull(message="Seats per row cannot be null")
    @Positive(message="Seats per row should be positive")
    private Integer seatsPerRow;

    public Integer getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(Integer numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public Integer getSeatsPerRow() {
        return seatsPerRow;
    }

    public void setSeatsPerRow(Integer seatsPerRow) {
        this.seatsPerRow = seatsPerRow;
    }

}
