package com.paymentplatform.embeddedpayments.auth.api;

import com.paymentplatform.embeddedpayments.auth.application.IssueMerchantTokenUseCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final IssueMerchantTokenUseCase issueMerchantTokenUseCase;

    public AuthController(IssueMerchantTokenUseCase issueMerchantTokenUseCase) {
        this.issueMerchantTokenUseCase = issueMerchantTokenUseCase;
    }

    @PostMapping("/token")
    public ResponseEntity<TokenResponse> issueToken(@Valid @RequestBody TokenRequest request) {
        IssueMerchantTokenUseCase.IssuedToken token = issueMerchantTokenUseCase.execute(request.merchantId());
        return ResponseEntity.ok(new TokenResponse(token.token(), token.expiresAt()));
    }

    public record TokenRequest(@NotNull UUID merchantId) {
    }

    public record TokenResponse(String token, Instant expiresAt) {
    }
}

