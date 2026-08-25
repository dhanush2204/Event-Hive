package com.EventHive.realtime.Exception;

public class VenueNotFoundException extends RuntimeException{
    public VenueNotFoundException(String message){
        super(message);
    }
}
