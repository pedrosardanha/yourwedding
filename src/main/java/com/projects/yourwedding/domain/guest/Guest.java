package com.projects.yourwedding.domain.guest;

import java.util.Objects;
import java.util.UUID;

public class Guest {
    private final UUID id;
    private final UUID weddingId;
    private UUID userId;
    private final String name;
    private final String code; 
    private final boolean isConfirmed;

    public Guest(UUID id, UUID weddingId, String name, String code, boolean isConfirmed) {
        if (id == null) throw new IllegalArgumentException("O ID do convidado não pode ser nulo.");
        if (weddingId == null) throw new IllegalArgumentException("O convidado precisa estar vinculado a um casamento.");
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("O nome do convidado não pode ser vazio.");
        if (code == null || code.trim().isEmpty()) throw new IllegalArgumentException("O código do convidado é obrigatório.");
        
        this.id = id;
        this.weddingId = weddingId;
        this.userId = null;
        this.name = name;
        this.code = code;
        this.isConfirmed = isConfirmed;
    }

    public UUID getId() { return id; }
    public UUID getWeddingId() { return weddingId; }
    public UUID getUserId() { return userId; }
    public String getName() { return name; }
    public String getCode() { return code; }
    public boolean isConfirmed() { return isConfirmed; }
    
    public Guest confirmAttendance() {
        return new Guest(this.id, this.weddingId, this.name, this.code, true);
    }

    public void linkToUser(UUID userId) {
        if (userId == null) {
            throw new IllegalArgumentException("O ID do usuário não pode ser nulo para a vinculação.");
        }
        this.userId = userId;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guest guest = (Guest) o;
        return Objects.equals(id, guest.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}