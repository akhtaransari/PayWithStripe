package com.portone.PayWithStripe.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentDetails{
    private Long amount;
    private String currencyType;
}
