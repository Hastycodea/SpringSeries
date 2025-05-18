package com.hastycode.RecapCRUD.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerDto {

    private int workerId;
    private String firstName;
    private String lastName;

}
