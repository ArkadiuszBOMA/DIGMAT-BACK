package com.codecool.dogmate.dto.lessons;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record NewLessonDto(
        @Size(min = 5, max = 50, message = "Nazwa lekcji musi mieć długość minimalną 5 i maksymalną 50 znaków")
        String name,
        @NotNull
        Integer trainingLevel,
        String description,
        byte [] imageLocation
){
}
