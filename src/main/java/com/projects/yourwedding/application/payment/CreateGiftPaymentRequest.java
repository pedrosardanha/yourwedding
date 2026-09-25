package com.projects.yourwedding.application.payment;

import com.projects.yourwedding.domain.payment.PaymentMethod;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateGiftPaymentRequest(
        UUID giftId,
        UUID userId,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String paymentToken
) {}