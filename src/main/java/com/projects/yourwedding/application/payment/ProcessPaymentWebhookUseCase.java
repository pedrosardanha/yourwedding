package com.projects.yourwedding.application.payment;

import com.projects.yourwedding.domain.gift.Gift;
import com.projects.yourwedding.domain.gift.GiftRepository;
import com.projects.yourwedding.domain.payment.GiftPayment;
import com.projects.yourwedding.domain.payment.GiftPaymentRepository;
import com.projects.yourwedding.domain.payment.PaymentStatus;

public class ProcessPaymentWebhookUseCase {

    private final GiftPaymentRepository paymentRepository;
    private final GiftRepository giftRepository;

    public ProcessPaymentWebhookUseCase(GiftPaymentRepository paymentRepository, GiftRepository giftRepository) {
        this.paymentRepository = paymentRepository;
        this.giftRepository = giftRepository;
    }

    public void execute(ProcessPaymentWebhookRequest request) {
        if (request.externalReference() == null || request.externalReference().isBlank()) {
            throw new IllegalArgumentException("A referência externa é obrigatória para processar o webhook.");
        }

        GiftPayment payment = paymentRepository.findByExternalReference(request.externalReference())
                .orElseThrow(() -> new IllegalArgumentException("Pagamento não encontrado para a referência informada."));

        if (payment.getStatus() == PaymentStatus.APPROVED) {
            return;
        }

        if ("approved".equalsIgnoreCase(request.status())) {

            payment.approve(request.externalReference());

            Gift gift = giftRepository.findById(payment.getGiftId())
                    .orElseThrow(() -> new IllegalStateException("Presente associado ao pagamento não encontrado."));

            gift.addRaisedAmount(payment.getAmount());

            giftRepository.save(gift);
            paymentRepository.save(payment);

        }

        else if ("rejected".equalsIgnoreCase(request.status()) || "cancelled".equalsIgnoreCase(request.status())) {
            payment.reject();
            paymentRepository.save(payment);
        }
    }
}