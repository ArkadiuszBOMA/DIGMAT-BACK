package com.codecool.dogmate.mapper;

public enum Gender {

    MALE(Boolean.TRUE),
    FEMALE(Boolean.FALSE);

    private final Boolean isMale;

    Gender(Boolean isMale) {
        this.isMale = isMale;
    }

    public Boolean getMale() {
        return isMale;
    }

}
