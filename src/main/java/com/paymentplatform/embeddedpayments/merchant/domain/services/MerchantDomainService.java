package com.paymentplatform.embeddedpayments.merchant.domain.services;

import com.paymentplatform.embeddedpayments.merchant.domain.entity.Merchant;
import com.paymentplatform.embeddedpayments.merchant.domain.repository.MerchantRepository;
import com.paymentplatform.embeddedpayments.shared.exception.DomainException;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class MerchantDomainService {

    private final MerchantRepository merchantRepository;

    public MerchantDomainService(MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
    }

    public Merchant buildNewMerchant(String name, String email) {
        merchantRepository.findByEmail(email).ifPresent(existing -> {
            throw new DomainException("merchant email already exists");
        });

        return new Merchant(UUID.randomUUID(), name, email, true);
    }
}

