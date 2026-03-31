package com.paymentplatform.embeddedpayments.auth.infrastructure.repository;

import com.paymentplatform.embeddedpayments.auth.domain.entity.AuthSession;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthSessionJpaRepository extends JpaRepository<AuthSession, UUID> {

    Optional<AuthSession> findByTokenAndRevokedFalse(String token);
}

