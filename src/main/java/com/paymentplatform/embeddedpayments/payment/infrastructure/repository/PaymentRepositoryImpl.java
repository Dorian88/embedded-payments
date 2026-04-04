package com.paymentplatform.embeddedpayments.payment.infrastructure.repository;

import com.paymentplatform.embeddedpayments.payment.domain.entity.PaymentIntent;
import com.paymentplatform.embeddedpayments.payment.domain.repository.PaymentRepository;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentRepositoryImpl implements PaymentRepository {

    private final PaymentJpaRepository jpaRepository;

    public PaymentRepositoryImpl(PaymentJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public PaymentIntent save(PaymentIntent paymentIntent) {
        return jpaRepository.save(paymentIntent);
    }

    @Override
    public Optional<PaymentIntent> findById(UUID id) {
        return jpaRepository.findById(id);
    }
}

