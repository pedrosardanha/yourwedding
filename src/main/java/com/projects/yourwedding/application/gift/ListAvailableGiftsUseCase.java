package com.projects.yourwedding.application.gift;

import com.projects.yourwedding.domain.gift.Gift;
import com.projects.yourwedding.domain.gift.GiftRepository;

import java.util.List;

public class ListAvailableGiftsUseCase {

    private final GiftRepository giftRepository;

    public ListAvailableGiftsUseCase(GiftRepository giftRepository) {
        this.giftRepository = giftRepository;
    }

    public List<AvailableGiftResponse> execute(ListAvailableGiftsRequest request) {
        if (request.weddingId() == null) {
            throw new IllegalArgumentException("O ID do casamento é obrigatório para buscar a lista de presentes.");
        }

        List<Gift> availableGifts = giftRepository.findAvailableGiftsByWeddingId(request.weddingId());

        return availableGifts.stream()
                .map(gift -> new AvailableGiftResponse(
                        gift.getId(),
                        gift.getName(),
                        gift.getPrice(),
                        gift.getRaisedAmount(),
                        gift.getPrice().subtract(gift.getRaisedAmount())
                ))
                .toList();
    }
}