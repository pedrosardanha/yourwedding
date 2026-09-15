package com.projects.yourwedding.application.wedding;

import com.projects.yourwedding.domain.wedding.Wedding;
import com.projects.yourwedding.domain.wedding.WeddingRepository;

import java.util.UUID;

public class CreateWeddingUseCase {

    private final WeddingRepository weddingRepository;

    public CreateWeddingUseCase(WeddingRepository weddingRepository) {
        this.weddingRepository = weddingRepository;
    }

    public CreateWeddingResponse execute(CreateWeddingRequest request) {
        // 1. Validação de Regra de Negócio: O código personalizado do evento precisa ser único
        if (weddingRepository.existsByCode(request.code())) {
            throw new IllegalArgumentException("Este código de casamento já está em uso. Escolha outro.");
        }

        // 2. Instanciação da Entidade
        Wedding newWedding = new Wedding(
                UUID.randomUUID(),
                request.code(),
                request.title(),
                request.date(),
                request.ownerId()
        );

        // 3. Persistência
        Wedding savedWedding = weddingRepository.save(newWedding);

        // 4. Mapeamento para o DTO de Saída
        return new CreateWeddingResponse(
                savedWedding.getId(),
                savedWedding.getTitle(),
                savedWedding.getDate(),
                savedWedding.getOwnerId(),
                savedWedding.getCode()
        );
    }
}
