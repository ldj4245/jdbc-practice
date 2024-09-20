package com.example.jdbcsandbox.repository;

import com.example.jdbcsandbox.domain.User;
import com.example.jdbcsandbox.jdbc.DataSourceConfig;
import com.example.jdbcsandbox.jdbc.JdbcConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,classes =  Repository.class))
@ComponentScan(basePackageClasses = {PersistenceModule.class})
@ContextConfiguration(classes = {JdbcConfig.class, DataSourceConfig.class})
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Order(1)
    @DisplayName("생성 후 조회할 수 있다.")
    void test(){
        //given
        userRepository.create("allie","female");
        userRepository.create("ben","male");
        userRepository.create("chris","male");
        userRepository.create("dany","male");


        //when
        List<User> male = userRepository.findByGender("male");


        //then
        assertThat(male.size()).isEqualTo(3);

    }

    @Test
    void test2(){
        //given
        userRepository.create("allie","female");
        userRepository.create("ben","male");
        userRepository.create("chris","male");
        userRepository.create("danny","male");

        userRepository.delete(8);


        //when
        List<User> male = userRepository.findByGender("male");

        //then
        assertThat(male.size()).isEqualTo(2);
    }



}