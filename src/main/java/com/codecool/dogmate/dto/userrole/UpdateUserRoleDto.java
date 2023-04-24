package com.codecool.dogmate.dto.userrole;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record UpdateUserRoleDto(
        @NotNull
        Integer id,
        @Size(min = 4, max = 10, message = "Nazwa typu użytkownika miary musi mieć długość minimalną 4 i maksymalną 10 znaków")
        String name
){
}
