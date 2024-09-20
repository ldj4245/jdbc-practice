package com.example.jdbcsandbox.exception;

public class SampleIdNotValidException extends RuntimeException {

    private String message;

    public SampleIdNotValidException(String message){
        super(message);

    }
}
