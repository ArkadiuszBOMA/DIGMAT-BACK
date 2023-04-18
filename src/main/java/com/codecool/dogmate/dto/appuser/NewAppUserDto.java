package com.codecool.dogmate.dto.appuser;


import javax.validation.constraints.Email;

public record NewAppUserDto(
        @Email
        String email,
        String password,
        String firstName,
        String lastName
){
}
