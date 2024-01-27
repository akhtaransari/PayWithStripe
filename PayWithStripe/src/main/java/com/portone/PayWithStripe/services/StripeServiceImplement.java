package com.portone.PayWithStripe.services;

import com.portone.PayWithStripe.model.PaymentDetails;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentIntentCollection;
import com.stripe.param.PaymentIntentCaptureParams;
import com.stripe.param.PaymentIntentCreateParams;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Getter
@Service
@Slf4j
public class StripeServiceImplement implements StripeServiceInterface {

    // Injecting the Stripe secret key from configuration
    @Value("${stripe.secret.key}")
    private String stripeSecretKey;

    /**
     * Create a payment intent based on the provided PaymentDetails.
     *
     * @param paymentDetails The payment details including amount and currency.
     * @return ResponseEntity containing the JSON representation of the created PaymentIntent or an error response.
     */
    @Override
    public ResponseEntity<String> createIntent(PaymentDetails paymentDetails) {
        try {
            // Set the Stripe API key
            Stripe.apiKey = stripeSecretKey;

            // Build PaymentIntentCreateParams using paymentDetails
            PaymentIntentCreateParams params =
                    PaymentIntentCreateParams.builder()
                            .setAmount(paymentDetails.getAmount())
                            .setCurrency(paymentDetails.getCurrencyType())
                            .setAutomaticPaymentMethods(
                                    PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
                                            .setEnabled(true)
                                            .build()
                            ).build();

            // Create PaymentIntent
            PaymentIntent paymentIntent = PaymentIntent.create(params);

            // Return the JSON representation of the created PaymentIntent
            return ResponseEntity.ok(paymentIntent.toJson());
        } catch (StripeException e) {
            // Log and handle any StripeException
            log.error("Error creating payment intent", e);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    /**
     * Capture a payment intent with the specified ID.
     *
     * @param id The ID of the PaymentIntent to capture.
     * @return ResponseEntity containing the JSON representation of the captured PaymentIntent or an error response.
     */
    @Override
    public ResponseEntity<String> captureIntent(String id) {
        try {
            // Set the Stripe API key
            Stripe.apiKey = stripeSecretKey;

            // Retrieve PaymentIntent by ID
            PaymentIntent resource = PaymentIntent.retrieve(id);

            // Build capture parameters
            PaymentIntentCaptureParams params = PaymentIntentCaptureParams.builder().build();

            // Capture the PaymentIntent
            PaymentIntent paymentIntent = resource.capture(params);

            // Return the JSON representation of the captured PaymentIntent
            return ResponseEntity.ok(paymentIntent.toJson());
        } catch (StripeException e) {
            // Log and handle any StripeException
            log.error("Error capturing payment intent with ID: {}", id, e);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    /**
     * Create a refund for a payment intent with the specified ID.
     *
     * @param id The ID of the PaymentIntent for which to create a refund.
     * @return ResponseEntity containing the JSON representation of the refunded PaymentIntent or an error response.
     */
    @Override
    public ResponseEntity<String> createRefund(String id) {
        try {
            // Set the Stripe API key
            Stripe.apiKey = stripeSecretKey;

            // Retrieve PaymentIntent by ID
            PaymentIntent intent = PaymentIntent.retrieve(id);

            // Cancel (create refund for) the PaymentIntent
            PaymentIntent refundedIntent = intent.cancel();

            // Return the JSON representation of the refunded PaymentIntent
            return ResponseEntity.ok(refundedIntent.toJson());
        } catch (StripeException e) {
            // Log and handle any StripeException
            log.error("Error creating refund for payment intent with ID: {}", id, e);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    /**
     * Get a list of PaymentIntents with limit specified in the params.
     *
     * @return ResponseEntity containing the JSON representation of the PaymentIntents or an error response.
     */
    @Override
    public ResponseEntity<String> getIntents() {
        try {
            // Set the Stripe API key
            Stripe.apiKey = stripeSecretKey;

            // Build parameters for listing PaymentIntents
            Map<String, Object> params = new HashMap<>();
            params.put("limit", 10);

            // List PaymentIntents
            PaymentIntentCollection paymentIntents = PaymentIntent.list(params);

            // Return the JSON representation of the PaymentIntents
            return ResponseEntity.ok(paymentIntents.toJson());
        } catch (StripeException e) {
            // Log and handle any StripeException
            log.error("Error fetching payment intents", e);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
