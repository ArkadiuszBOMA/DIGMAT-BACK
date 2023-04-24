package com.codecool.dogmate.dto.lessonanimal;

import java.time.LocalDateTime;

public record LessonAnimalDto(

    Integer id,
    Boolean finished,
    Integer animalId,
    Integer lessonId,
    LocalDateTime date_create,
    LocalDateTime date_modify,
    LocalDateTime date_archive,
    Boolean archive

){
}
