package com.codecool.dogmate.dto.appuser;


import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public record NewAppUserDto(
        @Size(min = 5, max = 50, message = "Imię musi mieć długość minimalną 5 i maksymalną 50 znaków")
        String firstName,
        @Size(min = 5, max = 50, message = "Nazwisko musi mieć długość minimalną 5 i maksymalną 50 znaków")
        String lastName,
        @Email
        String email,
        String password
){
    public void setPassword(String encodedPassword) {
    }
}
