package com.EventHive.realtime.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class VenueRequestDTO {
    @NotBlank(message="VenueName is required")
    private String venueName;
    @NotBlank(message="VenueAddress is required")
    private String venueAddress;
    @Positive(message="VenueCapacity should be positive")
    @NotNull(message="VenueCapacity can't be null")
    private int venueCapacity;

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getVenueAddress() {
        return venueAddress;
    }

    public void setVenueAddress(String venueAddress) {
        this.venueAddress = venueAddress;
    }

    public int getVenueCapacity() {
        return venueCapacity;
    }

    public void setVenueCapacity(int venueCapacity) {
        this.venueCapacity = venueCapacity;
    }

}
