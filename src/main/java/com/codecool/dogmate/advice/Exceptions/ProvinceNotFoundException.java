package com.codecool.dogmate.advice.Exceptions;


public class ProvinceNotFoundException extends RuntimeException{
    public ProvinceNotFoundException(Integer id) {
            super("Powiatu o id " + id + " nie znaleźliśmy");
        }
    public ProvinceNotFoundException(String name) {
        super("Powiatu o nazwie " + name + " nie znaleźliśmy");
    }
    }
