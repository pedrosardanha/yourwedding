package com.projects.yourwedding.application.gift;

import java.math.BigDecimal;
import java.util.UUID;

// DTO que representa um item da lista.
// Ocultamos a referência ao weddingId aqui pois a lista inteira já pertence a ele.
public record AvailableGiftResponse(
        UUID id,
        String name,
        BigDecimal price,
        BigDecimal raisedAmount,
        BigDecimal remainingAmount // Informação calculada para facilitar a tela do frontend
) {}