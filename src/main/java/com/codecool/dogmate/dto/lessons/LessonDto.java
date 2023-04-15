package com.codecool.dogmate.dto.lessons;

import java.time.LocalDateTime;

public record LessonDto(

    Integer id,
    String name,
    String trainingLevel,
    String description,
    String imageLocation,
    LocalDateTime date_create,
    LocalDateTime date_modify,
    LocalDateTime date_archive,
    Boolean archive

){
}
