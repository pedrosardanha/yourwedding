package com.projects.yourwedding.domain.wedding;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Wedding {
    private final UUID id;
    private final String code;
    private final String title;
    private final LocalDate date;
    private final UUID ownerId;

    public Wedding(UUID id, String code, String title, LocalDate date, UUID ownerId) {
        if (id == null) {
            throw new IllegalArgumentException("O ID não pode ser nulo.");
        }

        if (code == null || code.trim().isEmpty()) {
            throw new IllegalArgumentException("O código de busca do casamento não pode ser vazio.");
        }
        
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("O título do casamento não pode ser vazio.");
        }

        if (date == null) {
            throw new IllegalArgumentException("A data do casamento é obrigatória.");
        }

        if (ownerId == null) {
            throw new IllegalArgumentException("O casamento precisa estar vinculado a um usuário (dono).");
        }
        
        if (date.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("A data do casamento deve ser no futuro.");
        }

        this.id = id;
        this.code = code;
        this.title = title;
        this.date = date;
        this.ownerId = ownerId;
    }

    public UUID getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDate() {
        return date;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wedding wedding = (Wedding) o;
        return Objects.equals(id, wedding.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
