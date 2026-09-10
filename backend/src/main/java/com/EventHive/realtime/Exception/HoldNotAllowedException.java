package com.EventHive.realtime.Exception;

public class HoldNotAllowedException extends RuntimeException{
    public HoldNotAllowedException(String message){
        super(message);
    }
}
