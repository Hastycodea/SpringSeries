package com.example.recapcrud.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "mavitu")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int price;
}
