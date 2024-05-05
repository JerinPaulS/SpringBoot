package com.example.demo.dao;

import com.example.demo.entities.Rank;
import com.example.demo.json.Officer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfficerRepository extends JpaRepository<Officer, Integer> {

    List<Officer> findAllByLastNameContainingAndRank(String last, Rank rank);
}
