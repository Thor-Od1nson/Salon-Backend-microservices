package com.vishal.payment_service.exception;

public class PaymentOrderNotFoundException extends Exception {
    public PaymentOrderNotFoundException(String msg){
        super(msg);
    }
}
