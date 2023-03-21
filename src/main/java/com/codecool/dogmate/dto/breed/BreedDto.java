package com.codecool.dogmate.dto.breed;

import java.time.LocalDateTime;

public record BreedDto(

        Integer id,
        String name,
        Integer animalType,
        LocalDateTime date_create,
        LocalDateTime date_modify,
        LocalDateTime date_archive,
        Boolean archive


){
}
