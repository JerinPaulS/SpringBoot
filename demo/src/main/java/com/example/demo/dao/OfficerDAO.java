package com.example.demo.dao;

import com.example.demo.json.Officer;

import java.util.List;
import java.util.Optional;

public interface OfficerDAO {

    Officer save(Officer officer);

    Optional<Officer> findById(Integer id);

    List<Officer> finadAll();

    long count();

    void delete(Officer officer);

    boolean existsById(Integer id);
}
