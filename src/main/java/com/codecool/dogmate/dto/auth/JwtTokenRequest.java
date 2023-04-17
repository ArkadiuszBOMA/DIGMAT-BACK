package com.codecool.dogmate.dto.auth;

import javax.validation.constraints.NotBlank;

public record JwtTokenRequest(
        @NotBlank(message = "email nie może być pusty")
        String email,
        @NotBlank(message = "hasło nie może być puste")
        String password
) {
}
