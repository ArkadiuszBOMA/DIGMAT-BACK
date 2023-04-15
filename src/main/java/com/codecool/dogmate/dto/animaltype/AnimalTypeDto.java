package com.codecool.dogmate.dto.animaltype;

import java.time.LocalDateTime;

public record AnimalTypeDto(
        Integer id,
        String name,
        String description,
        LocalDateTime date_create,
        LocalDateTime date_modify,
        LocalDateTime date_archive,
        Boolean archive
) {
}
