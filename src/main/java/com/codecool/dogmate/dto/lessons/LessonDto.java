package com.codecool.dogmate.dto.lessons;

import com.codecool.dogmate.dto.animal.AnimalDto;
import com.codecool.dogmate.dto.city.CityDto;
import com.codecool.dogmate.dto.lessonanimal.LessonAnimalDto;
import com.codecool.dogmate.dto.lessonsteps.LessonStepDto;
import com.codecool.dogmate.entity.Animal;
import com.codecool.dogmate.entity.LessonStep;
import com.codecool.dogmate.entity.LessonsAnimal;

import java.time.LocalDateTime;
import java.util.List;

public record LessonDto(

    Integer id,
    String name,
    String trainingLevel,
    String description,
    String imageLocation,
    LocalDateTime date_create,
    LocalDateTime date_modify,
    LocalDateTime date_archive,
    Boolean archive,
    List<LessonStepDto> lessonSteps

){
}
