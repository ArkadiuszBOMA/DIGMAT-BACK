package com.codecool.dogmate.dto.animal;

import com.codecool.dogmate.mapper.Gender;

public record NewAnimalDto(
    String name,
    Integer animalTypesId,
    Integer breedId,
    Integer userId,
    Integer birthYear,
    String pictureLocation,
    String description,
    Gender gender
){
}
