package com.example.jdbcsandbox.repository;


import com.example.jdbcsandbox.JdbcSandboxApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = JdbcSandboxApplication.class)
public class IntergrationConfig {
}
