package com.projects.yourwedding.domain.payment;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GiftPaymentRepository {
    GiftPayment save(GiftPayment payment);

    Optional<GiftPayment> findById(UUID id);

    Optional<GiftPayment> findByExternalReference(String externalReference);

    List<GiftPayment> findByGiftId(UUID giftId);
}