package com.projects.yourwedding.application.user;

import com.projects.yourwedding.domain.user.*;

import java.util.UUID;

public class CreateUserUseCase {

    private final UserRepository userRepository;
    private final DisposableEmailChecker emailChecker;
    private final PasswordEncoder passwordEncoder;

    public CreateUserUseCase(
            UserRepository userRepository, 
            DisposableEmailChecker emailChecker,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.emailChecker = emailChecker;
        this.passwordEncoder = passwordEncoder;
    }

    // Agora recebemos o Request DTO e devolvemos o Response DTO
    public CreateUserResponse execute(CreateUserRequest request) {
        
        Email email = new Email(request.email());

        // 1. Validações de Negócio
        if (emailChecker.isDisposable(email.getAddress())) {
            throw new IllegalArgumentException("E-mails temporários não são permitidos.");
        }

        if (userRepository.existsByEmail(email.getAddress())) {
            throw new IllegalArgumentException("E-mail já cadastrado no sistema.");
        }

        // 2. Criptografia
        String hashedPassword = passwordEncoder.encode(request.password());

        // 3. Criação da Entidade
        User newUser = new User(UUID.randomUUID(), request.name(), email, hashedPassword);
        
        // 4. Persistência
        User savedUser = userRepository.save(newUser);
        
        // 5. Mapeamento para o DTO de Saída (Protegendo os dados sensíveis)
        return new CreateUserResponse(
            savedUser.getId(),
            savedUser.getName(),
            savedUser.getEmail().getAddress()
        );
    }
}