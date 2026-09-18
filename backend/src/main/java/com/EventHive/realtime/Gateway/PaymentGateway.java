package com.EventHive.realtime.Gateway;

public interface PaymentGateway {
    PaymentInitiationResult initiatePayment(String reference,int amount);
}
