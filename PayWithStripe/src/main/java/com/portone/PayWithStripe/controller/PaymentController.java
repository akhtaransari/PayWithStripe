package com.portone.PayWithStripe.controller;

import com.portone.PayWithStripe.model.PaymentDetails;
import com.portone.PayWithStripe.services.StripeServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class PaymentController {

    @Autowired
    private StripeServiceInterface stripeServiceInterface;

    @PostMapping("/create_intent")
    public ResponseEntity<String> createIntent(@RequestBody PaymentDetails paymentDetails) {
        log.info("Payment Intent created successfully for {}", paymentDetails);
        return stripeServiceInterface.createIntent(paymentDetails);
    }

    @PostMapping("/capture_intent/{id}")
    public ResponseEntity<String> captureIntent(@PathVariable String id) {
        log.info("Capturing payment intent with ID: {}", id);
        return stripeServiceInterface.captureIntent(id);
    }

    @PostMapping("/create_refund/{id}")
    public ResponseEntity<String> createRefund(@PathVariable String id) {
        log.info("Creating refund for payment intent with ID: {}", id);
        return stripeServiceInterface.createRefund(id);
    }

    @GetMapping("/get_intents")
    public ResponseEntity<String> getIntents() {
        log.info("Fetching list of payment intents");
        return stripeServiceInterface.getIntents();
    }
}