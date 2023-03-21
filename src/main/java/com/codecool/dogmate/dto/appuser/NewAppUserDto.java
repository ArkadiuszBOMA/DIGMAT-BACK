package com.codecool.dogmate.dto.appuser;


public record NewAppUserDto(
    String firstName,
    String lastName,
    String email,
    String password,
    Integer userType,
    Integer cityId
){
    public void setPassword(String encodedPassword) {
    }
}
