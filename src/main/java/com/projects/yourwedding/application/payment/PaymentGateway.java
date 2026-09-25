package com.projects.yourwedding.application.payment;

import com.projects.yourwedding.domain.payment.GiftPayment;
import com.projects.yourwedding.domain.payment.PaymentMethod;

public interface PaymentGateway {
    GatewayTransactionResult process(GiftPayment payment, PaymentMethod method, String paymentToken);
}