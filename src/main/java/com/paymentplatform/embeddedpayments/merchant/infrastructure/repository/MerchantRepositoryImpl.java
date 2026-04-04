package com.paymentplatform.embeddedpayments.merchant.infrastructure.repository;

import com.paymentplatform.embeddedpayments.merchant.domain.entity.Merchant;
import com.paymentplatform.embeddedpayments.merchant.domain.repository.MerchantRepository;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class MerchantRepositoryImpl implements MerchantRepository {

    private final MerchantJpaRepository jpaRepository;

    public MerchantRepositoryImpl(MerchantJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Merchant save(Merchant merchant) {
        return jpaRepository.save(merchant);
    }

    @Override
    public Optional<Merchant> findById(UUID merchantId) {
        return jpaRepository.findById(merchantId);
    }

    @Override
    public Optional<Merchant> findByEmail(String email) {
        return jpaRepository.findByEmail(email);
    }
}

