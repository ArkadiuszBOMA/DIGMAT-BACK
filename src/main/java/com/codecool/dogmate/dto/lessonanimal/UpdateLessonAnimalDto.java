package com.codecool.dogmate.dto.lessonanimal;


import javax.validation.constraints.NotNull;

public record UpdateLessonAnimalDto(
        @NotNull
        Integer id,
        @NotNull
        Integer animalId,
        @NotNull
        Integer lessonId
        ){
}
