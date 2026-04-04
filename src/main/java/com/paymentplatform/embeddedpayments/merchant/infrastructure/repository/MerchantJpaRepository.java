package com.paymentplatform.embeddedpayments.merchant.infrastructure.repository;

import com.paymentplatform.embeddedpayments.merchant.domain.entity.Merchant;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantJpaRepository extends JpaRepository<Merchant, UUID> {

    Optional<Merchant> findByEmail(String email);
}

