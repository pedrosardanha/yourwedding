package com.projects.yourwedding.application.guest;

import java.util.UUID;

public record AddGuestResponse(
    UUID id,
    UUID weddingId,
    String name,
    String code,
    boolean confirmed
) {}
