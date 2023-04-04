package com.codecool.dogmate.dto.breed;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public record UpdateBreedDto(
        @NotNull
        Integer id,
        @Size(min = 5, max = 55, message = "Rasa zwierzaka musi mieć długość minimalną 5 i maksymalną 55 znaków")
        String name,
        @NotNull(message = "Nie wybrałeś typu zwierzęcia")
        String animalType
) {
}
