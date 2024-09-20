package com.example.jdbcsandbox.controller;


import com.example.jdbcsandbox.domain.Dummy;
import com.example.jdbcsandbox.repository.DummyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@RestController
public class DummyController {

    private final DummyRepository dummyRepository;



    @GetMapping("/api/v1/dummy/create")
    public Dummy create(@RequestParam String name){
        Dummy dummy = new Dummy();
        dummy.setName(name);

        return dummyRepository.save(dummy);
    }

    @GetMapping("/api/v1/dummy/delete")
    public int delete(@RequestParam int id){
        return dummyRepository.deleteById(id);
    }
}
