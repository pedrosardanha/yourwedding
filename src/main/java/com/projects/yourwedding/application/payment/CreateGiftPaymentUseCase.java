package com.projects.yourwedding.application.payment;

import com.projects.yourwedding.domain.gift.Gift;
import com.projects.yourwedding.domain.gift.GiftRepository;
import com.projects.yourwedding.domain.payment.GiftPayment;
import com.projects.yourwedding.domain.payment.GiftPaymentRepository;
import com.projects.yourwedding.domain.payment.PaymentMethod;
import com.projects.yourwedding.domain.payment.PaymentStatus;

import java.util.UUID;

public class CreateGiftPaymentUseCase {

    private final GiftPaymentRepository paymentRepository;
    private final GiftRepository giftRepository;
    private final PaymentGateway paymentGateway; // Nova Porta Injetada

    public CreateGiftPaymentUseCase(
            GiftPaymentRepository paymentRepository,
            GiftRepository giftRepository,
            PaymentGateway paymentGateway
    ) {
        this.paymentRepository = paymentRepository;
        this.giftRepository = giftRepository;
        this.paymentGateway = paymentGateway;
    }

    public CreateGiftPaymentResponse execute(CreateGiftPaymentRequest request) {

        if (request.paymentMethod() == PaymentMethod.CREDIT_CARD && (request.paymentToken() == null || request.paymentToken().isBlank())) {
            throw new IllegalArgumentException("O token do cartão é obrigatório para pagamentos via crédito.");
        }

        Gift gift = giftRepository.findById(request.giftId())
                .orElseThrow(() -> new IllegalArgumentException("O presente selecionado não foi encontrado."));

        GiftPayment payment = new GiftPayment(
                UUID.randomUUID(),
                gift.getId(),
                request.userId(),
                request.amount(),
                request.paymentMethod(),
                PaymentStatus.PENDING,
                null
        );

        GatewayTransactionResult gatewayResult = paymentGateway.process(payment, request.paymentMethod(), request.paymentToken());

        if ("approved".equalsIgnoreCase(gatewayResult.status())) {
            payment.approve(gatewayResult.externalReference());
        }

        GiftPayment savedPayment = paymentRepository.save(payment);

        return new CreateGiftPaymentResponse(
                savedPayment.getId(),
                savedPayment.getGiftId(),
                savedPayment.getAmount(),
                savedPayment.getStatus().name(),
                gatewayResult.pixQrCodeBase64(),
                gatewayResult.pixCopiaECola()
        );
    }
}