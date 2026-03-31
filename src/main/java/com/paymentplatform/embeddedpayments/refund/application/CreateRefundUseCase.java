package com.paymentplatform.embeddedpayments.refund.application;

import com.paymentplatform.embeddedpayments.refund.domain.entity.Refund;
import com.paymentplatform.embeddedpayments.refund.domain.repository.RefundRepository;
import com.paymentplatform.embeddedpayments.refund.domain.services.RefundDomainService;
import java.math.BigDecimal;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class CreateRefundUseCase {

    private final RefundRepository refundRepository;
    private final RefundDomainService refundDomainService;

    public CreateRefundUseCase(RefundRepository refundRepository,
                               RefundDomainService refundDomainService) {
        this.refundRepository = refundRepository;
        this.refundDomainService = refundDomainService;
    }

    public Refund execute(UUID transactionId, BigDecimal amount, String reason) {
        Refund refund = refundDomainService.create(transactionId, amount, reason);
        return refundRepository.save(refund);
    }
}

