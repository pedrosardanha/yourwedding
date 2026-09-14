package com.projects.yourwedding.domain.user;

public interface PasswordEncoder {
    
    String encode(String rawPassword);
    
    boolean matches(String rawPassword, String encodedPassword);
}