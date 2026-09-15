package com.projects.yourwedding.application.guest;

import com.projects.yourwedding.domain.guest.Guest;
import com.projects.yourwedding.domain.guest.GuestRepository;

import java.security.SecureRandom;
import java.util.UUID;

public class AddGuestUseCase {

    private final GuestRepository guestRepository;
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGTH = 6;
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final int MAX_RETRIES = 10;

    public AddGuestUseCase(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public AddGuestResponse execute(AddGuestRequest request) {
        // 1. Gera um código único garantindo que não há colisão no banco
        String uniqueCode = generateUniqueCode();

        // 2. Validação de Regra de Negócio: O código personalizado do evento precisa ser único
        if (guestRepository.existsByCode(uniqueCode)) {
            throw new IllegalArgumentException("Este código de casamento já está em uso. Escolha outro.");
        }

        // 3. Instancia o Guest com confirmed = false por padrão
        Guest newGuest = new Guest(
                UUID.randomUUID(),
                request.weddingId(),
                request.name(),
                uniqueCode,
                false
        );

        // 4. Persiste no banco de dados
        Guest savedGuest = guestRepository.save(newGuest);

        // 5. Retorna o DTO de resposta
        return new AddGuestResponse(
                savedGuest.getId(),
                savedGuest.getWeddingId(),
                savedGuest.getName(),
                savedGuest.getCode(),
                savedGuest.isConfirmed()
        );
    }

    private String generateUniqueCode() {
        String code;
        int attempts = 0;

        do {
            if (attempts >= MAX_RETRIES) {
                throw new IllegalStateException("O sistema está sob alta carga ou os códigos se esgotaram. Tente adicionar o convidado novamente.");
            }
            code = generateRandomCode();
            attempts++;
        } while (guestRepository.existsByCode(code));

        return code;
    }

    private String generateRandomCode() {
        StringBuilder sb = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            sb.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }
}