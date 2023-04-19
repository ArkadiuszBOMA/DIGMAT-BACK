package com.codecool.dogmate.dto.auth;

public record JwtTokenResponse(
        Integer id,
        String firstName,
        String lastName,
        String email,
        String avatarSmallLocation,
        String token) {
}
