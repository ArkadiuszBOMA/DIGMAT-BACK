package com.codecool.dogmate.dto.city;

import java.time.LocalDateTime;

public record CityDto(
        Integer id,
        String name,
        LocalDateTime date_create,
        LocalDateTime date_modify,
        LocalDateTime date_archive,
        Boolean archive,
        Integer province
) {
}
