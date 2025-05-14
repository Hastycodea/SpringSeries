package com.example.recapcrud.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserDto {
    @JsonProperty("index_number")
    private int id;
    private String username;
}
