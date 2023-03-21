package com.codecool.dogmate.dto.animaltype;

import com.codecool.dogmate.dto.breed.BreedDto;

import java.time.LocalDateTime;
import java.util.List;

public record AnimalTypeDto(
        Integer id,
        String name,
        String description,
        LocalDateTime date_create,
        LocalDateTime date_modify,
        LocalDateTime date_archive,
        Boolean archive,
        List<BreedDto>breeds
) {
}
