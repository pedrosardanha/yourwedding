package com.projects.yourwedding.domain.guest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GuestRepository {
    
    Guest save(Guest guest);
    
    Optional<Guest> findById(UUID id);
    
    Optional<Guest> findByCode(String code);
    
    boolean existsByCode(String code);
    
    List<Guest> findByWeddingId(UUID weddingId);
    
    List<Guest> findAllByUserId(UUID userId);
    
    void deleteById(UUID id);
}