package com.paymentplatform.embeddedpayments.payment.application;

import com.paymentplatform.embeddedpayments.payment.domain.entity.PaymentIntent;
import com.paymentplatform.embeddedpayments.payment.domain.repository.PaymentRepository;
import com.paymentplatform.embeddedpayments.payment.domain.services.PaymentDomainService;
import java.math.BigDecimal;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class CreatePaymentIntentUseCase {

    private final PaymentRepository paymentRepository;
    private final PaymentDomainService paymentDomainService;

    public CreatePaymentIntentUseCase(PaymentRepository paymentRepository,
                                      PaymentDomainService paymentDomainService) {
        this.paymentRepository = paymentRepository;
        this.paymentDomainService = paymentDomainService;
    }

    public PaymentIntent execute(UUID merchantId, BigDecimal amount, String currency) {
        PaymentIntent intent = paymentDomainService.createPaymentIntent(merchantId, amount, currency);
        return paymentRepository.save(intent);
    }
}

