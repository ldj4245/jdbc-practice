package com.example.jdbcsandbox.repository;

import com.example.jdbcsandbox.domain.Dummy;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface DummyRepository {

    Dummy save(Dummy dummy);

    Optional<Dummy> findByName(String name);

    int updateNameById(int id, String name);

    int deleteById(int id);
}
