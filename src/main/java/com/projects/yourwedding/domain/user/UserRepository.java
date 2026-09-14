package com.projects.yourwedding.domain.user;

public interface UserRepository {

    User save(User user);

    User findByEmail(Email email);

    User findById(Long id);

    boolean existsByEmail(String email);
}
