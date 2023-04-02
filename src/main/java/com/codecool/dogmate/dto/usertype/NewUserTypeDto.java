package com.codecool.dogmate.dto.usertype;


import javax.validation.constraints.Size;

public record NewUserTypeDto(
        @Size(min = 5, max = 10, message = "Nazwa typu użytkowniak miary musi mieć długość minimalną 5 i maksymalną 10 znaków")
        String name
){
}
