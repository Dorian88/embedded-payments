package com.paymentplatform.embeddedpayments;

import com.paymentplatform.embeddedpayments.auth.api.AuthController;
import com.paymentplatform.embeddedpayments.merchant.api.MerchantController;
import com.paymentplatform.embeddedpayments.payment.api.PaymentController;
import com.paymentplatform.embeddedpayments.refund.api.RefundController;
import com.paymentplatform.embeddedpayments.transaction.api.TransactionController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EmbeddedPaymentsApplicationTests {

    @Autowired
    private AuthController authController;

    @Autowired
    private MerchantController merchantController;

    @Autowired
    private PaymentController paymentController;

    @Autowired
    private TransactionController transactionController;

    @Autowired
    private RefundController refundController;

    @Test
    void contextLoads() {
        assertThat(authController).isNotNull();
        assertThat(merchantController).isNotNull();
        assertThat(paymentController).isNotNull();
        assertThat(transactionController).isNotNull();
        assertThat(refundController).isNotNull();
    }

}
