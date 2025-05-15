package com.hastycode.RecapCRUD.mappers;

import com.hastycode.RecapCRUD.dtos.WorkerDto;
import com.hastycode.RecapCRUD.model.Worker;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkerMapper {
    WorkerDto toDto(Worker worker);
}
