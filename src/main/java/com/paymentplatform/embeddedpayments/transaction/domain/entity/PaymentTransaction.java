package com.paymentplatform.embeddedpayments.transaction.domain.entity;

import com.paymentplatform.embeddedpayments.shared.audit.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "payment_transaction")
public class PaymentTransaction extends AuditableEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private UUID paymentIntentId;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Instant processedAt;

    protected PaymentTransaction() {
    }

    public PaymentTransaction(UUID id, UUID paymentIntentId, BigDecimal amount, String status, Instant processedAt) {
        this.id = id;
        this.paymentIntentId = paymentIntentId;
        this.amount = amount;
        this.status = status;
        this.processedAt = processedAt;
    }

    public UUID getId() {
        return id;
    }

    public UUID getPaymentIntentId() {
        return paymentIntentId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }

    public Instant getProcessedAt() {
        return processedAt;
    }
}

