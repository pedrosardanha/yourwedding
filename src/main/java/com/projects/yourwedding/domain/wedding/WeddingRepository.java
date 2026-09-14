package com.projects.yourwedding.domain.wedding;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WeddingRepository {
    Wedding save(Wedding wedding);

    Optional<Wedding> findById(UUID id);

    Optional<Wedding> findByCode(String code);

    boolean existsByCode(String code);

    List<Wedding> findAllByOwnerId(UUID ownerId);
}
