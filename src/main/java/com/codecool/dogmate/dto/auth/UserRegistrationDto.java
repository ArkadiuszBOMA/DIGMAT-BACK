package com.codecool.dogmate.dto.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record UserRegistrationDto(
        @NotBlank(message = "email nie może być pusty")
        @Email
        String email,
        @Size(min = 10, message = "hasło musi mieć minimum 10 znaków")
        String password
) {
}
