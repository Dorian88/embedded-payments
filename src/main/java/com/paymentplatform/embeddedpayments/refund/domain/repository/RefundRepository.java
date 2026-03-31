package com.paymentplatform.embeddedpayments.refund.domain.repository;

import com.paymentplatform.embeddedpayments.refund.domain.entity.Refund;
import java.util.Optional;
import java.util.UUID;

public interface RefundRepository {

    Refund save(Refund refund);

    Optional<Refund> findById(UUID id);
}

