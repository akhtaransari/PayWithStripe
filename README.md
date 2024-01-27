# PayWithStripe - Java Spring Boot

PayWithStripe is a Java Spring Boot backend service designed to facilitate integration with the Stripe Payment Gateway. This project implements backend APIs for creating, capturing, refunding payment intents, and fetching a list of payment intents.

## Table of Contents
- [Requirements](#requirements)
- [API Endpoints](#api-endpoints)
- [Service Implementation](#service-implementation)
- [Testing](#testing)
- [Configuration](#configuration)
- [References](#references)

## Requirements

- Implement backend APIs for Stripe Payment Gateway integration using Java Spring Boot.
- Create a  account on Stripe to obtain access and secret keys.
- Implement the following APIs:

### API Endpoints

1. **Create Intent for Payment**
    - Endpoint: `POST /api/v1/create_intent`
    - Request Body: [PaymentDetails](#payment-details)

2. **Capture the Created Intent**
    - Endpoint: `POST /api/v1/capture_intent/{id}`
    - Path Variable: `id` - ID of the created intent

3. **Create a Refund for the Created Intent**
    - Endpoint: `POST /api/v1/create_refund/{id}`
    - Path Variable: `id` - ID of the created intent

4. **Get a List of All Intents**
    - Endpoint: `GET /api/v1/get_intents`

#### Payment Details
```json
{
  "your_payment_details": "go_here"
}
```

## Service Implementation

### StripeServiceImplement

The `StripeServiceImplement` class is a Spring Service responsible for interacting with the Stripe API to handle payment intents. It implements the `StripeServiceInterface` for creating, capturing, refunding payment intents, and fetching a list of payment intents.

#### Methods

- `createIntent`: Create a payment intent based on the provided PaymentDetails.
- `captureIntent`: Capture a payment intent with the specified ID.
- `createRefund`: Create a refund for a payment intent with the specified ID.
- `getIntents`: Get a list of PaymentIntents with a specified limit.

#### Configuration

The Stripe secret key is injected into the service from the application configuration.

```yaml
# application.properties or application.yml
stripe.secret.key=your_stripe_secret_key
```

#### Logging

The service uses SLF4J for logging. Logs include information about successful operations and errors.

### Usage

To use the service, inject `StripeServiceInterface` and call the appropriate methods.

Example:

```java
@RestController
@RequestMapping("/api/v1")
public class PaymentController {

    @Autowired
    private StripeServiceInterface stripeService;

    @PostMapping("/create_intent")
    public ResponseEntity<String> createIntent(@RequestBody PaymentDetails paymentDetails) {
        return stripeService.createIntent(paymentDetails);
    }

    // Other controller methods for capture, refund, and get_intents
}
```

## Testing

### PayWithStripeApplicationTests

The `PayWithStripeApplicationTests` class contains test cases for the functionality implemented in the Spring Boot application. These tests use JUnit and Mockito for unit testing.

#### Test Cases

- `createIntent_Success`: Verify that the `createIntent` method returns the expected success message.
- `captureIntent_Success`: Verify that the `captureIntent` method returns the expected success message.
- `createRefund_Success`: Verify that the `createRefund` method returns the expected success message.
- `getIntents_Success`: Verify that the `getIntents` method returns the expected success message.

#### Configuration

The test class is annotated with `@SpringBootTest`, indicating that it is a Spring Boot test. It also uses `@MockBean` to create a mock instance of `StripeServiceImplement` for testing.

```java
@SpringBootTest
class PayWithStripeApplicationTests {
    // Test methods...
}
```

## Configuration

Ensure that you have configured your Stripe secret key in the `application.properties` or `application.yml` file.

## References

1. [Stripe API Docs](https://stripe.com/docs/api/payment_intents)
2. [Payment Intents](https://stripe.com/docs/payments/payment-intents)
3. [Stripe Java SDK](https://github.com/stripe/stripe-java)
4. [Setting up a Simple Backend in Spring Boot](https://spring.io/guides/gs/spring-boot/)
5. [Testing in Spring Boot](https://spring.io/guides/gs/testing-web/)

Additional reference links for building and testing REST APIs:

- [Building and Testing a REST API in Spring Boot](https://www.baeldung.com/spring-boot-testing)
- [RESTful Web Services with Spring Boot](https://www.baeldung.com/spring-boot-rest-api-tutorial)

Feel free to adapt and customize the information based on your project's specific needs.
