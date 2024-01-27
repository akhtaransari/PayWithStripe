package com.portone.PayWithStripe.services;

import com.portone.PayWithStripe.model.PaymentDetails;
import org.springframework.http.ResponseEntity;


public interface StripeServiceInterface {

    ResponseEntity<String> createIntent(PaymentDetails paymentDetails);

    ResponseEntity<String> captureIntent(String id);

    ResponseEntity<String> createRefund(String id);

    ResponseEntity<String> getIntents();
}
