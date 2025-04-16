package com.kinya.kinya_backend.auth.dtos;

import com.kinya.kinya_backend.user.UserRole;
import jakarta.validation.constraints.NotNull;

import java.util.Optional;

public record SignUpDto(
        @NotNull
        String name,

        @NotNull
        String email,

        @NotNull
        String password,
        Optional<UserRole> role
) {
}

