package com.paymentplatform.embeddedpayments.auth.domain.services;

import com.paymentplatform.embeddedpayments.auth.domain.entity.AuthSession;
import com.paymentplatform.embeddedpayments.shared.exception.DomainException;
import java.time.Instant;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class AuthDomainService {

    public AuthSession createSession(UUID merchantId, String token, Instant expiresAt) {
        if (merchantId == null) {
            throw new DomainException("merchantId is required");
        }

        if (expiresAt.isBefore(Instant.now())) {
            throw new DomainException("token expiration must be in the future");
        }

        return new AuthSession(UUID.randomUUID(), merchantId, token, expiresAt, false);
    }
}

