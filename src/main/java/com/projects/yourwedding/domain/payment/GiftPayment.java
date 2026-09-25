package com.projects.yourwedding.domain.payment;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class GiftPayment {

    private final UUID id;
    private final UUID giftId;
    private final UUID userId;
    private final BigDecimal amount;
    private final PaymentMethod paymentMethod;
    private PaymentStatus status;
    private String externalReference;

    public GiftPayment(UUID id, UUID giftId, UUID userId, BigDecimal amount, PaymentMethod paymentMethod, PaymentStatus status, String externalReference) {
        if (id == null) {
            throw new IllegalArgumentException("O ID do pagamento não pode ser nulo.");
        }
        if (giftId == null) {
            throw new IllegalArgumentException("O ID do presente não pode ser nulo.");
        }
        if (userId == null) {
            throw new IllegalArgumentException("O ID do usuário pagador não pode ser nulo.");
        }
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor do pagamento deve ser maior que zero.");
        }
        if (paymentMethod == null) {
            throw new IllegalArgumentException("O método de pagamento é obrigatório.");
        }

        this.id = id;
        this.giftId = giftId;
        this.userId = userId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = status != null ? status : PaymentStatus.PENDING;
        this.externalReference = externalReference;
    }

    public void approve(String externalReference) {
        if (this.status == PaymentStatus.APPROVED) {
            throw new IllegalStateException("Este pagamento já foi aprovado.");
        }
        this.status = PaymentStatus.APPROVED;
        this.externalReference = externalReference;
    }

    public void reject() {
        this.status = PaymentStatus.REJECTED;
    }

    // Getters
    public UUID getId() { return id; }
    public UUID getGiftId() { return giftId; }
    public UUID getUserId() { return userId; }
    public BigDecimal getAmount() { return amount; }
    public PaymentMethod getPaymentMethod() { return paymentMethod; }
    public PaymentStatus getStatus() { return status; }
    public String getExternalReference() { return externalReference; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GiftPayment payment = (GiftPayment) o;
        return Objects.equals(id, payment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}