package com.codecool.dogmate.dto.animal;

import com.codecool.dogmate.dto.lessonanimal.LessonAnimalDto;
import com.codecool.dogmate.mapper.Gender;

import java.time.LocalDateTime;
import java.util.List;

public record AnimalDto (

    Integer id,
    String name,
    String animalTypesId,
    String breedId,
    String userFullName,
    Integer birthYear,
    String pictureLocation,
    String description,
    Gender gender,
    LocalDateTime date_create,
    LocalDateTime date_modify,
    LocalDateTime date_archive,
    Boolean archive,
    List<LessonAnimalDto> lessonAnimal
){
}
