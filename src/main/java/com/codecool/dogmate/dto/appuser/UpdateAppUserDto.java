package com.codecool.dogmate.dto.appuser;


import com.codecool.dogmate.dto.animal.AnimalDto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;
import java.util.List;

public record UpdateAppUserDto(
        @NotNull
        Integer id,
        @Size(min = 5, max = 50, message = "Imię musi mieć długość minimalną 5 i maksymalną 50 znaków")
        String firstName, String lastName,
        @Email
        String email,
        String password,
        @NotNull
        String profilePictureLocation,
        String avatarSmallLocation,
        String userType,
        String city,
        String description,
        Boolean isLocked,
        Boolean isBanned,
        OffsetDateTime banExpiration,
        Boolean isActive,
        List<AnimalDto> animals
){
    public void setPassword(String encodedPassword) {
    }
}
