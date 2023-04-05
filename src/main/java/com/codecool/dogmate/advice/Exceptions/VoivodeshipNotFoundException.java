package com.codecool.dogmate.advice.Exceptions;


public class VoivodeshipNotFoundException extends RuntimeException{
    public VoivodeshipNotFoundException(Integer id) {
            super("Województwa o id " + id + " nie znaleźliśmy");
        }
    public VoivodeshipNotFoundException(String name) {
        super("Województwa o nazwie " + name + " nie znaleźliśmy");
    }
}
