package com.paymentplatform.embeddedpayments.merchant.domain.repository;

import com.paymentplatform.embeddedpayments.merchant.domain.entity.Merchant;
import java.util.Optional;
import java.util.UUID;

public interface MerchantRepository {

    Merchant save(Merchant merchant);

    Optional<Merchant> findById(UUID merchantId);

    Optional<Merchant> findByEmail(String email);
}

