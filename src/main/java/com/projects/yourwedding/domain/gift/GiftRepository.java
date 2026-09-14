package com.projects.yourwedding.domain.gift;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GiftRepository {
    Gift save(Gift gift);

    Optional<Gift> findById(UUID id);

    List<Gift> findByWeddingId(UUID weddingId);

    List<Gift> findAvailableGiftsByWeddingId(UUID weddingId);

    void deleteById(UUID id);
}
