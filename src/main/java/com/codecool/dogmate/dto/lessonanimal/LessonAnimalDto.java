package com.codecool.dogmate.dto.lessonanimal;

import java.time.LocalDateTime;

public record LessonAnimalDto(

    Integer id,
    Boolean finished,
    Integer animalId,
    Integer lessonId,
    LocalDateTime date_archive,
    LocalDateTime date_modify,
    LocalDateTime date_create,
    Boolean archive

){
}
