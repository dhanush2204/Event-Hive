package com.EventHive.realtime.Exception;

public class InvalidEventStateException extends RuntimeException{
    public InvalidEventStateException(String message){
        super(message);
    }
}
