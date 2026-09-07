package com.EventHive.realtime.Exception;

public class BookingNotAllowedException extends RuntimeException{
    public BookingNotAllowedException(String message){
        super(message);
    }
}
