package com.EventHive.realtime.Service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.EventHive.realtime.Entity.Hold;
import com.EventHive.realtime.Entity.Payment;
import com.EventHive.realtime.Enum.PaymentStatus;
import com.EventHive.realtime.Gateway.*;
import com.EventHive.realtime.JpaRepository.PaymentRepository; 

import jakarta.transaction.Transactional;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepo;
    private final PaymentGateway paymentGateway;
    @Transactional
    public Payment initiatePayment(Hold hold,int amount){
        Payment payment=new Payment();
        payment.setHold(hold);
        payment.setAmount(amount);
        payment.setStatus(PaymentStatus.PENDING);
        payment.setCreatedAt(LocalDateTime.now());

        PaymentInitiationResult result=paymentGateway.initiatePayment("HOLD-"+hold.getHoldId(),amount);
        payment.setGatewayOrderId(result.getGatewayOrderId());

        return paymentRepo.save(payment);
    }
}
