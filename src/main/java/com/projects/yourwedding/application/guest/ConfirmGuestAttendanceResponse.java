package com.projects.yourwedding.application.guest;

import java.util.UUID;

public record ConfirmGuestAttendanceResponse(
    UUID id,
    String name,
    boolean isConfirmed,
    UUID userId
) {}