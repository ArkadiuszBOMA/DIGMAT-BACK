package com.codecool.dogmate.advice.Exceptions;


public class TrainingLevelNotFoundException extends RuntimeException{
    public TrainingLevelNotFoundException(Integer id) {
            super("Poziomu trudności lekcji o id " + id + " nie znaleźliśmy");
        }
    public TrainingLevelNotFoundException(String name) {
        super("Poziomu trudności lekcji o nazwie " + name + " nie znaleźliśmy");
    }
    }
