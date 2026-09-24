package com.projects.yourwedding.application.gift;

import java.util.UUID;

public record ListAvailableGiftsRequest(
    UUID weddingId
) {}
