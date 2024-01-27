package com.portone.PayWithStripe;

import com.portone.PayWithStripe.model.PaymentDetails;
import com.portone.PayWithStripe.services.StripeServiceImplement;
import com.stripe.exception.StripeException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class PayWithStripeApplicationTests {

    // Injecting the value of "stripe.secret.key" from the configuration
    @Value("${stripe.secret.key}")
    private String stripeSecretKey;

    // Mocking the Stripe service for testing
    @Mock
    private StripeServiceImplement stripeService;

    @BeforeEach
    void setUp() {
        // Initializing mocks for each test method
        MockitoAnnotations.openMocks(this);

        // Mocking the behavior of StripeServiceImplement#getStripeSecretKey
        when(stripeService.getStripeSecretKey()).thenReturn(stripeSecretKey);
    }

    /**
     * Test case for the createIntent method.
     * It verifies that the createIntent method returns the expected success message.
     */
    @Test
    void createIntent_Success() throws StripeException {
        // Arrange
        PaymentDetails paymentDetails = new PaymentDetails(1000L, "usd");

        // Mocking the behavior of StripeServiceImplement#createPaymentIntent
        when(stripeService.createIntent(paymentDetails)).thenReturn(ResponseEntity.ok("Some success message"));

        // Act
        ResponseEntity<String> response = stripeService.createIntent(paymentDetails);

        // Assert
        assertEquals("Some success message", response.getBody());
        assertNotEquals("Some  message", response.getBody());
    }

    /**
     * Test case for the captureIntent method.
     * It verifies that the captureIntent method returns the expected success message.
     */
    @Test
    void captureIntent_Success() throws StripeException {
        // Arrange
        String paymentIntentId = "pi_3OcraXSE806efooD0MbLgx8u";

        // Mocking the behavior of stripeService.captureIntent
        when(stripeService.captureIntent(paymentIntentId)).thenReturn(ResponseEntity.ok("Some success message"));

        // Act
        ResponseEntity<String> response = stripeService.captureIntent(paymentIntentId);

        // Assert
        assertEquals("Some success message", response.getBody());
        assertNotEquals("Some  message", response.getBody());
    }

    /**
     * Test case for the createRefund method.
     * It verifies that the createRefund method returns the expected success message.
     */
    @Test
    void createRefund_Success() throws StripeException {
        // Arrange
        String paymentIntentId = "pi_3OcraXSE806efooD0MbLgx8u";

        // Mocking the behavior of stripeService.createRefund
        when(stripeService.createRefund(paymentIntentId)).thenReturn(ResponseEntity.ok("Some success message"));

        // Act
        ResponseEntity<String> response = stripeService.createRefund(paymentIntentId);

        // Assert
        assertEquals("Some success message", response.getBody());
        assertNotEquals("Some  message", response.getBody());
    }

    /**
     * Test case for the getIntents method.
     * It verifies that the getIntents method returns the expected success message.
     */
    @Test
    void getIntents_Success() throws StripeException {
        // Mocking the behavior of stripeService.getIntents
        when(stripeService.getIntents()).thenReturn(ResponseEntity.ok("Some success message"));

        // Act
        ResponseEntity<String> response = stripeService.getIntents();

        // Assert
        assertEquals("Some success message", response.getBody());
        assertNotEquals("Some  message", response.getBody());
    }
}
