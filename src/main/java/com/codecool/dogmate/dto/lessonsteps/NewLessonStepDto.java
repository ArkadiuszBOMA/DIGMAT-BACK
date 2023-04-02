package com.codecool.dogmate.dto.lessonsteps;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record NewLessonStepDto(
        @Size(min = 5, max = 50, message = "Nazwa kroku lekcja musi mieć długość minimalną 5 i maksymalną 50 znaków")
        String name,
        @NotNull
        Integer lesson,
        String description,
        Integer stepNumber
){
}
