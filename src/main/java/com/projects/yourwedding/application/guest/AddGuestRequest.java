package com.projects.yourwedding.application.guest;

import java.util.UUID;

public record AddGuestRequest(
    UUID weddingId,
    String name
) {}
