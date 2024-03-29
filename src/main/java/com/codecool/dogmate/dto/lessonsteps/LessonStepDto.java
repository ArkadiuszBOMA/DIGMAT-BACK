package com.codecool.dogmate.dto.lessonsteps;

import java.time.LocalDateTime;

public record LessonStepDto(

    Integer id,
    String name,
    String lesson,
    String description,
    Integer stepNumber,
    LocalDateTime date_create,
    LocalDateTime date_modify,
    LocalDateTime date_archive,
    Boolean archive

){
}
