package com.paymentplatform.embeddedpayments.transaction.application;

import com.paymentplatform.embeddedpayments.transaction.domain.entity.PaymentTransaction;
import com.paymentplatform.embeddedpayments.transaction.domain.repository.TransactionRepository;
import com.paymentplatform.embeddedpayments.transaction.domain.services.TransactionDomainService;
import java.math.BigDecimal;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class CreateTransactionUseCase {

    private final TransactionRepository transactionRepository;
    private final TransactionDomainService transactionDomainService;

    public CreateTransactionUseCase(TransactionRepository transactionRepository,
                                    TransactionDomainService transactionDomainService) {
        this.transactionRepository = transactionRepository;
        this.transactionDomainService = transactionDomainService;
    }

    public PaymentTransaction execute(UUID paymentIntentId, BigDecimal amount) {
        PaymentTransaction transaction = transactionDomainService.register(paymentIntentId, amount);
        return transactionRepository.save(transaction);
    }
}

