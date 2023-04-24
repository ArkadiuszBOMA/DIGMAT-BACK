package com.codecool.dogmate.dto.userprivilege;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record UpdateUserPrivilegeDto(
        @NotNull
        Integer id,
        @Size(min = 3, max = 10, message = "Nazwa roli musi mieć długość minimalną 3 i maksymalną 10 znaków")
        String name
){
}
