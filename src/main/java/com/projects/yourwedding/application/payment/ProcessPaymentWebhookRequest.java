package com.projects.yourwedding.application.payment;

public record ProcessPaymentWebhookRequest(
        String externalReference,
        String status
) {}