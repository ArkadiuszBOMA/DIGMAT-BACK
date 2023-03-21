package com.codecool.dogmate.dto.appuser;


import com.codecool.dogmate.dto.animal.AnimalDto;
import com.codecool.dogmate.entity.Animal;

import java.time.OffsetDateTime;
import java.util.List;

public record AppUserDto(

        Integer id,
        String firstName,
        String lastName,
        String email,
        String profilePictureLocation,
        String avatarSmallLocation,
        Integer userType,
        Integer city,
        String description,
        Boolean isLocked,
        Boolean isBanned,
        OffsetDateTime banExpiration,
        Boolean isActive,
        List<AnimalDto> animals
){
}
