package com.projects.yourwedding.application.user;

import java.util.UUID;

// Este é o pacote de dados seguro que devolvemos para a internet (sem a senha!)
public record CreateUserResponse(
    UUID id,
    String name,
    String email
) {}