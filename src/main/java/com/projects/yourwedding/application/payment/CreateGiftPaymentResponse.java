package com.projects.yourwedding.application.payment;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateGiftPaymentResponse(
        UUID paymentId,
        UUID giftId,
        BigDecimal amount,
        String status,
        String pixQrCodeBase64,
        String pixCopiaECola
) {}