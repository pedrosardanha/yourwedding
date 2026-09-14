package com.projects.yourwedding.application.wedding;

import java.time.LocalDate;
import java.util.UUID;

public record CreateWeddingRequest(
    String title,
    LocalDate date,
    UUID ownerId,
    String code
) {}