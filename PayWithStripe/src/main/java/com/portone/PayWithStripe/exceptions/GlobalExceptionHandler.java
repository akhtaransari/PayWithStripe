package com.portone.PayWithStripe.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * Handles NoHandlerFoundException and returns a ResponseEntity with an ErrorDetail object.
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorDetail> noHandler(NoHandlerFoundException ex, WebRequest wr) {
        log.warn("NoHandlerFoundException: " + ex.getMessage(), ex);
        return new ResponseEntity<>(new ErrorDetail(ex.getMessage(), wr.getDescription(false), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles generic Exception and returns a ResponseEntity with an ErrorDetail object.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetail> parentException(Exception ex, WebRequest wr) {
        log.warn("Exception: " + ex.getMessage(), ex);
        return new ResponseEntity<>(new ErrorDetail(ex.getMessage(), wr.getDescription(false), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles PayWithStripeException and returns a ResponseEntity with an ErrorDetail object.
     */
    @ExceptionHandler(PayWithStripeException.class)
    public ResponseEntity<ErrorDetail> payWithStripeException(PayWithStripeException ex, WebRequest wr) {
        log.warn("PayWithStripeException: " + ex.getMessage(), ex);
        return new ResponseEntity<>(new ErrorDetail(ex.getMessage(), wr.getDescription(false), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }
}
