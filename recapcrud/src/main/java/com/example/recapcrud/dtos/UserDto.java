package com.example.recapcrud.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public class UserDto {
    @JsonProperty("index_number")
    private int id;
    private String username;

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
