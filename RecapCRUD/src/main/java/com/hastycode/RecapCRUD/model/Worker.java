package com.hastycode.RecapCRUD.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "memories")
@AllArgsConstructor
@NoArgsConstructor
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int workerId;
    public String firstName;
    public String lastName;

    public String imageName;
    public String imageType;
    @Lob
    public byte[] imageData;

}
