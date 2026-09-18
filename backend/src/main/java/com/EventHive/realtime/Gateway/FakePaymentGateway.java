package com.EventHive.realtime.Gateway;

public class FakePaymentGateway implements PaymentGateway{
    @Override
    PaymentInitiationResult initiatePayment(String reference,int amount){
        PaymentInitiationResult result=new PaymentInitiationResult();
        result.setGatewayorderId("FAKE-"+reference);
        result.setAmount(amount);
        return result;
    }
}
