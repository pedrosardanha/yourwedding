package com.projects.yourwedding.domain.gift;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class Gift {

    private final UUID id;
    private final UUID weddingId;
    private final String name;
    private final BigDecimal price;
    private BigDecimal raisedAmount;

    public Gift(UUID id, UUID weddingId, String name, BigDecimal price, BigDecimal raisedAmount) {
        if (id == null) {
            throw new IllegalArgumentException("O ID do presente não pode ser nulo.");
        }
        if (weddingId == null) {
            throw new IllegalArgumentException("O presente precisa estar vinculado a um casamento.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do presente não pode ser vazio.");
        }
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O preço do presente deve ser maior que zero.");
        }

        this.id = id;
        this.weddingId = weddingId;
        this.name = name;
        this.price = price;
        this.raisedAmount = raisedAmount != null ? raisedAmount : BigDecimal.ZERO;
    }

    public void addFunds(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor a ser adicionado deve ser maior que zero.");
        }
        
        BigDecimal newTotal = this.raisedAmount.add(amount);
        if (newTotal.compareTo(this.price) > 0) {
            throw new IllegalStateException("O valor arrecadado não pode ultrapassar o preço do presente.");
        }
        
        this.raisedAmount = newTotal;
    }

    public boolean isFullyFunded() {
        return this.raisedAmount.compareTo(this.price) >= 0;
    }

    // Getters
    public UUID getId() { return id; }
    public UUID getWeddingId() { return weddingId; }
    public String getName() { return name; }
    public BigDecimal getPrice() { return price; }
    public BigDecimal getRaisedAmount() { return raisedAmount; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gift gift = (Gift) o;
        return Objects.equals(id, gift.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
