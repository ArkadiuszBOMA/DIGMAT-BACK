package com.codecool.dogmate.dto.animal;

import com.codecool.dogmate.mapper.Gender;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record UpdateAnimalDto(
        @NotNull
        Integer id,
        @Size(min = 5, max = 50, message = "Imię zwierzaka musi mieć długość minimalną 5 i maksymalną 50 znaków")
        String name,
        @NotNull(message = "Nie wybrałeś typu zwierzęcia")
        Integer animalTypesId,
        @NotNull(message = "Nie wybrałeś rasy ")
        Integer breedId,
        @NotNull(message = "Nie podałeś właściciela ")
        Integer userId,
        Integer birthYear,
        String pictureLocation,
        String description,
        Gender gender
){
}
