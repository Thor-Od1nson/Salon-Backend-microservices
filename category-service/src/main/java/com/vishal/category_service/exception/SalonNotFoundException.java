package com.vishal.category_service.exception;

public class SalonNotFoundException extends RuntimeException {

    public SalonNotFoundException(String msg){
        super(msg);
    }
}
