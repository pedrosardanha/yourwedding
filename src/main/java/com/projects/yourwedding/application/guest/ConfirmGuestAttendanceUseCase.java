package com.projects.yourwedding.application.guest;

import com.projects.yourwedding.domain.guest.Guest;
import com.projects.yourwedding.domain.guest.GuestRepository;

public class ConfirmGuestAtenddanceUseCase {

    private final GuestRepository guestRepository;

    public ConfirmGuestAtenddanceUseCase(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public ConfirmGuestAttendanceResponse execute(ConfirmGuestAttendanceRequest request) {
        if (request.code() == null || request.code().trim().isEmpty()) {
            throw new IllegalArgumentException("O código de confirmação é obrigatório.");
        }

        if (request.loggedInUserId() == null) {
            throw new IllegalArgumentException("O usuário precisa estar logado para confirmar presença.");
        }

        Guest guest = guestRepository.findByCode(request.code())
                .orElseThrow(() -> new IllegalArgumentException("Código inválido ou não encontrado. Verifique o convite e tente novamente."));

        guest.confirmAttendance();
        guest.linkToUser(request.loggedInUserId());

        Guest savedGuest = guestRepository.save(guest);

        return new ConfirmGuestAttendanceResponse(
                savedGuest.getId(),
                savedGuest.getName(),
                savedGuest.isConfirmed(),
                savedGuest.getUserId()
        );
    }
}