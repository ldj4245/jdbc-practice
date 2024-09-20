package com.example.jdbcsandbox.exception;


import com.example.jdbcsandbox.controller.MyResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExcpetionAdvice {

    @ExceptionHandler(RuntimeException.class)
    public MyResponse<String> handleRuntimeExcpetion(RuntimeException re){
        return MyResponse.fail(
                re.getMessage()
        );
    }

    @ExceptionHandler(SampleIdNotValidException.class)
    public MyResponse<String> handleSampleIdNotValidExcpetion(SampleIdNotValidException e){
        return MyResponse.fail(e.getMessage());
    }
}
