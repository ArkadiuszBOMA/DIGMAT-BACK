package com.codecool.dogmate.controller.advice.Exceptions;


public class VoivodeshipsNotFoundException extends RuntimeException{
    public VoivodeshipsNotFoundException(Integer id) {
            super("Województwo o id " + id + " nie znaleźliśmy");
        }
    }
