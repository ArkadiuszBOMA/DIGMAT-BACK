package com.codecool.dogmate.dto.lessons;

public record NewLessonDto(
    String name,
    Integer trainingLevel,
    String description,
    String imageLocation
){
}
