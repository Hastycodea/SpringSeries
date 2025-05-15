package com.hastycode.RecapCRUD.dtos;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WorkerDto {

    private int workerId;
    private String firstName;
    private String lastName;

}
