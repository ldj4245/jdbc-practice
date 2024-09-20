package com.example.jdbcsandbox.controller;


import com.example.jdbcsandbox.domain.Test;
import com.example.jdbcsandbox.exception.SampleIdNotValidException;
import com.example.jdbcsandbox.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private MyValidator myValidator;

    @GetMapping("/api/v1/test{testId}")
    public String getTest(@PathVariable Integer testId) {
        return "testId";
    }

    @GetMapping("/api/v2/test/{testId}")
    public String getTest2(@PathVariable Integer testId,
                            @RequestParam String name) {

        return "testId2";


    }

    @PostMapping("/api/v3/test/{testId}")
    public MyResponse<String> getTest3(
                @PathVariable Integer testId,
                @RequestParam String name,
                @RequestBody Test test,
                BindingResult bindingResult


    ){
        myValidator.validate(test,bindingResult);
        if(bindingResult.hasErrors()){
            throw new SampleIdNotValidException("sample id not valid exception.");
        }

        return MyResponse.success(testService.test(test));

    }
}
