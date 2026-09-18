package com.EventHive.realtime.Gateway;
import lombok.*;
@Getter
@Setter
public class PaymentInitiationResult {
    private String gatewayOrderId;
    private int amount;
}
