package com.hastycode.RecapCRUD.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "workers")
@AllArgsConstructor
@NoArgsConstructor
public class Worker {

    @Id
    public int workerId;
    public String firstName;
    public String lastName;


}
