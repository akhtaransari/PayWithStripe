package com.portone.PayWithStripe.exceptions;

public class PayWithStripeException extends RuntimeException {
    PayWithStripeException(String message) {
        super(message);
    }
}
