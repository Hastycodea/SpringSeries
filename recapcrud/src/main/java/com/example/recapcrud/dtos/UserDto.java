package com.example.recapcrud.dtos;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserDto {
    private int id;
    private String username;
}
