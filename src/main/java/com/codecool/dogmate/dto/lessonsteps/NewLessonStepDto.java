package com.codecool.dogmate.dto.lessonsteps;

public record NewLessonStepDto(
        String name,
        Integer lesson,
        String description,
        Integer stepNumber
){
}
