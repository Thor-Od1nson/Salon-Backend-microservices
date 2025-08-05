package com.vishal.saloon_service.exception;

public class SalonNotFoundException extends RuntimeException{

    public SalonNotFoundException(String msg){
        super(msg);
    }
}
