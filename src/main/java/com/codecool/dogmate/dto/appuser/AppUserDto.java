package com.codecool.dogmate.dto.appuser;


import java.time.OffsetDateTime;

public record AppUserDto(

        Integer id,
        String firstName,
        String lastName,
        String email,
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
