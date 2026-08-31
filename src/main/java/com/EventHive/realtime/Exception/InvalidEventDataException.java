package com.EventHive.realtime.Exception;

public class InvalidEventDataException extends RuntimeException{
    public InvalidEventDataException(String message){
        super(message);
    }
}
