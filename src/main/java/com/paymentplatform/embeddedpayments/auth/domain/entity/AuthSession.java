package com.paymentplatform.embeddedpayments.auth.domain.entity;

import com.paymentplatform.embeddedpayments.shared.audit.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "auth_session")
public class AuthSession extends AuditableEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private UUID merchantId;

    @Column(nullable = false, length = 1024)
    private String token;

    @Column(nullable = false)
    private Instant expiresAt;

    @Column(nullable = false)
    private boolean revoked;

    protected AuthSession() {
    }

    public AuthSession(UUID id, UUID merchantId, String token, Instant expiresAt, boolean revoked) {
        this.id = id;
        this.merchantId = merchantId;
        this.token = token;
        this.expiresAt = expiresAt;
        this.revoked = revoked;
    }

    public UUID getId() {
        return id;
    }

    public UUID getMerchantId() {
        return merchantId;
    }

    public String getToken() {
        return token;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public boolean isRevoked() {
        return revoked;
    }
}

