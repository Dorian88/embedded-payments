package com.paymentplatform.embeddedpayments.auth.application;

import com.paymentplatform.embeddedpayments.auth.domain.entity.AuthSession;
import com.paymentplatform.embeddedpayments.auth.domain.repository.AuthSessionRepository;
import com.paymentplatform.embeddedpayments.auth.domain.services.AuthDomainService;
import com.paymentplatform.embeddedpayments.shared.security.JwtTokenService;
import java.time.Instant;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class IssueMerchantTokenUseCase {

    private final AuthSessionRepository authSessionRepository;
    private final AuthDomainService authDomainService;
    private final JwtTokenService jwtTokenService;

    public IssueMerchantTokenUseCase(AuthSessionRepository authSessionRepository,
                                     AuthDomainService authDomainService,
                                     JwtTokenService jwtTokenService) {
        this.authSessionRepository = authSessionRepository;
        this.authDomainService = authDomainService;
        this.jwtTokenService = jwtTokenService;
    }

    public IssuedToken execute(UUID merchantId) {
        String token = jwtTokenService.generateMerchantToken(merchantId);
        Instant expiresAt = jwtTokenService.extractExpiration(token);

        AuthSession session = authDomainService.createSession(merchantId, token, expiresAt);
        authSessionRepository.save(session);

        return new IssuedToken(session.getToken(), session.getExpiresAt());
    }

    public record IssuedToken(String token, Instant expiresAt) {
    }
}


