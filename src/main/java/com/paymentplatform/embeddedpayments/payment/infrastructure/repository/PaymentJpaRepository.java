package com.paymentplatform.embeddedpayments.payment.infrastructure.repository;

import com.paymentplatform.embeddedpayments.payment.domain.entity.PaymentIntent;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentJpaRepository extends JpaRepository<PaymentIntent, UUID> {
}

