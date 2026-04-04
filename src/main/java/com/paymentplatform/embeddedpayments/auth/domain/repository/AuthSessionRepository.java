package com.paymentplatform.embeddedpayments.auth.domain.repository;

import com.paymentplatform.embeddedpayments.auth.domain.entity.AuthSession;
import java.util.Optional;

public interface AuthSessionRepository {

    AuthSession save(AuthSession authSession);

    Optional<AuthSession> findByTokenAndRevokedFalse(String token);
}

