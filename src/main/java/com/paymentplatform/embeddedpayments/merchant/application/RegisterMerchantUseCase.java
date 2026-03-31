package com.paymentplatform.embeddedpayments.merchant.application;

import com.paymentplatform.embeddedpayments.merchant.domain.entity.Merchant;
import com.paymentplatform.embeddedpayments.merchant.domain.repository.MerchantRepository;
import com.paymentplatform.embeddedpayments.merchant.domain.services.MerchantDomainService;
import org.springframework.stereotype.Service;

@Service
public class RegisterMerchantUseCase {

    private final MerchantRepository merchantRepository;
    private final MerchantDomainService merchantDomainService;

    public RegisterMerchantUseCase(MerchantRepository merchantRepository,
                                   MerchantDomainService merchantDomainService) {
        this.merchantRepository = merchantRepository;
        this.merchantDomainService = merchantDomainService;
    }

    public Merchant execute(String name, String email) {
        Merchant merchant = merchantDomainService.buildNewMerchant(name, email);
        return merchantRepository.save(merchant);
    }
}

