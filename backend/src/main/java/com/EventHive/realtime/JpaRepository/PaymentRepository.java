package com.EventHive.realtime.JpaRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EventHive.realtime.Entity.Payment;
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{
    Optional<Payment> findByHold_HoldId(Long holdId);

    Optional<Payment> findByGatewayOrderId(String gatewayOrderId);

    Optional<Payment> findByGatewayTransactionId(String gatewayTransactionId);
}
