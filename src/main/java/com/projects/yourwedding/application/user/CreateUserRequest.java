package com.projects.yourwedding.application.user;

public record CreateUserRequest(
    String name,
    String email,
    String password
) {}
