package com.example.jdbcsandbox.service;


import com.example.jdbcsandbox.domain.Test;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class TestService {
    public String test(@Valid Test test){
        return "test test";

    }
}
