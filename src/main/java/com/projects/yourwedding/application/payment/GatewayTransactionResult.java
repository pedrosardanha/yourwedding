package com.projects.yourwedding.application.payment;

public record GatewayTransactionResult(
        String externalReference,
        String status,
        String pixQrCodeBase64,
        String pixCopiaECola
) {}