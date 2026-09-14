package com.projects.yourwedding.domain.user;

import java.util.Objects;
import java.util.regex.Pattern;

public class Email {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-z]{2,}$";
    private static final Pattern PATTERN = Pattern.compile(EMAIL_REGEX);

    private final String address;

    public Email(String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("O e-mail não pode ser vazio.");
        }
        if (!PATTERN.matcher(address).matches()) {
            throw new IllegalArgumentException("Formato de e-mail inválido.");
        }
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(address, email.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }
}