package com.portone.PayWithStripe.exceptions;

import java.time.LocalDateTime;

public record ErrorDetail(String message, String description, LocalDateTime timeStamp) {
}
