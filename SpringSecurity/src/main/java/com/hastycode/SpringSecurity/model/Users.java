package com.hastycode.SpringSecurity.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "securityUsers")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;

}
