package com.codecool.dogmate.dto.userrole;


import javax.validation.constraints.Size;

public record NewUserRoleDto(
        @Size(min = 4, max = 10, message = "Nazwa typu użytkownika miary musi mieć długość minimalną 5 i maksymalną 10 znaków")
        String name
){
}
