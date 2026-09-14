package com.projects.yourwedding.domain.user;

import java.util.Objects;
import java.util.UUID;

public class User {
    private final UUID id;
    private final String name;
    private final Email email;
    private final String password;

    public User(UUID id, String name, Email email, String password) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do usuário não pode ser vazio.");
        }

        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("A senha não pode ser vazia.");
        }

        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public Email getEmail() { return email; }
    public String getPassword() { return password; }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
