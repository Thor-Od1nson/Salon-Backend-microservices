package com.vishal.service_offring_service.exception;

import com.vishal.service_offring_service.payload.response.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(SalonNotFoundException.class)
    public ResponseEntity<ExceptionResponse> ExceptionHandler(Exception ex, WebRequest req){
        ExceptionResponse response=new com.vishal.service_offring_service.payload.response.ExceptionResponse(ex.getMessage(), req.getDescription(false), LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(ServiceOfferingNotFoundException.class)
    public ResponseEntity<ExceptionResponse> serviceOfferingExceptionHandler(Exception ex, WebRequest req){
        ExceptionResponse response=new com.vishal.service_offring_service.payload.response.ExceptionResponse(ex.getMessage(), req.getDescription(false), LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ExceptionResponse> categoryExceptionHandler(Exception ex, WebRequest req){
        ExceptionResponse response=new com.vishal.service_offring_service.payload.response.ExceptionResponse(ex.getMessage(), req.getDescription(false), LocalDateTime.now());
        return ResponseEntity.ok(response);
    }
}
