package com.codecool.dogmate.dto.lessonanimal;


import javax.validation.constraints.NotNull;

public record NewLessonAnimalDto(
        @NotNull
        Integer animalId,
        @NotNull
        Integer lessonId
        ){
}
