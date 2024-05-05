package com.example.demo.json;

import com.example.demo.entities.Rank;

public record Officer (Integer id,
                       Rank rank,
                       String firstName,
                       String lastName) {

}
