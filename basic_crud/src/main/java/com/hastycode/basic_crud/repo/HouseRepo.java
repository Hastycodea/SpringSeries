package com.hastycode.basic_crud.repo;

import com.hastycode.basic_crud.model.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepo extends JpaRepository<House, Integer> {
}
