package com.example.jdbcsandbox.domain;


import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Dummy {
    @Id
    private Integer id;

    private String name;
}
