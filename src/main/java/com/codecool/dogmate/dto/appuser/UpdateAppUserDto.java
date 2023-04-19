package com.codecool.dogmate.dto.appuser;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;

public record UpdateAppUserDto(
        @NotNull
        Integer id,
        @Size(min = 5, max = 50, message = "Imię musi mieć długość minimalną 5 i maksymalną 50 znaków")
        String firstName, String lastName,
        @Email
        String email,
        String password,
        String profilePictureLocation,
        String avatarSmallLocation,
        String city,
        String description,
        Boolean isLocked,
        Boolean isBanned,
        OffsetDateTime banExpiration,
        Boolean isActive
){
}
