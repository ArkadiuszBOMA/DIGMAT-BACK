package com.codecool.dogmate.dto.traininglevel;

import javax.validation.constraints.Size;

public record NewTrainingLevelDto(

        @Size(min = 5, max = 50, message = "Nazwa poziomu trudności miary musi mieć długość minimalną 5 i maksymalną 50 znaków")
        String name,
        String description
){
}
