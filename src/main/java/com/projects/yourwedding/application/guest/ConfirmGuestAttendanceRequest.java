package com.projects.yourwedding.application.guest;

import java.util.UUID;

public record ConfirmGuestAttendanceRequest(
    String code,
    UUID loggedInUserId 
) {}