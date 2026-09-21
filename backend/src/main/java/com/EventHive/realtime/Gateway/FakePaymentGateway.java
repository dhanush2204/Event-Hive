package com.EventHive.realtime.Gateway;

public class FakePaymentGateway implements PaymentGateway{
    @Override
    public PaymentInitiationResult initiatePayment(String reference,int amount){
        PaymentInitiationResult result=new PaymentInitiationResult();
        result.setGatewayOrderId("FAKE-"+reference);
        result.setAmount(amount);
        return result;
    }
}
