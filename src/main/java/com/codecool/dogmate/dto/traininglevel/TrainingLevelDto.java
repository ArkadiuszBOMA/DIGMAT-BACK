package com.codecool.dogmate.dto.traininglevel;

import java.time.LocalDateTime;

public record TrainingLevelDto(

    Integer id,
    String name,
    String description,
    LocalDateTime date_create,
    LocalDateTime date_modify,
    LocalDateTime date_archive,
    Boolean archive
){
}
