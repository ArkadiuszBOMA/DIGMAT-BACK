package com.codecool.dogmate.dto.usertype;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record UpdateUserTypeDto(
        @NotNull
        Integer id,
        @Size(min = 5, max = 10, message = "Nazwa typu użytkownika miary musi mieć długość minimalną 5 i maksymalną 10 znaków")
        String name
){
}
