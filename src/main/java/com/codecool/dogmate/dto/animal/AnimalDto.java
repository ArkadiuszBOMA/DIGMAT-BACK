package com.codecool.dogmate.dto.animal;

import com.codecool.dogmate.mapper.Gender;

import java.time.LocalDateTime;

public record AnimalDto (

    Integer id,
    String name,
    Integer animalTypesId,
    Integer breedId,
    Integer userId,
    Integer birthYear,
    String pictureLocation,
    String description,
    Gender gender,
    LocalDateTime date_create,
    LocalDateTime date_modify,
    LocalDateTime date_archive,
    Boolean archive
){
}
