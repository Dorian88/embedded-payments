package com.paymentplatform.embeddedpayments.transaction.infrastructure.repository;

import com.paymentplatform.embeddedpayments.transaction.domain.entity.PaymentTransaction;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionJpaRepository extends JpaRepository<PaymentTransaction, UUID> {
}

