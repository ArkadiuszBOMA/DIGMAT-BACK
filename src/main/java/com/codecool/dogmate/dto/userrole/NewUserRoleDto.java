package com.codecool.dogmate.dto.userrole;


import javax.validation.constraints.Size;

public record NewUserRoleDto(
        @Size(min = 5, max = 10, message = "Nazwa roli musi mieć długość minimalną 5 i maksymalną 10 znaków")
        String name
){
}
