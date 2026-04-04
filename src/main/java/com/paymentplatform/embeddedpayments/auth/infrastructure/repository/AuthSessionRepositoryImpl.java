package com.paymentplatform.embeddedpayments.auth.infrastructure.repository;

import com.paymentplatform.embeddedpayments.auth.domain.entity.AuthSession;
import com.paymentplatform.embeddedpayments.auth.domain.repository.AuthSessionRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class AuthSessionRepositoryImpl implements AuthSessionRepository {

    private final AuthSessionJpaRepository jpaRepository;

    public AuthSessionRepositoryImpl(AuthSessionJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public AuthSession save(AuthSession authSession) {
        return jpaRepository.save(authSession);
    }

    @Override
    public Optional<AuthSession> findByTokenAndRevokedFalse(String token) {
        return jpaRepository.findByTokenAndRevokedFalse(token);
    }
}

