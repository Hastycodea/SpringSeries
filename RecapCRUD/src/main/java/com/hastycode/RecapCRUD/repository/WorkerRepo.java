package com.hastycode.RecapCRUD.repository;


import com.hastycode.RecapCRUD.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerRepo extends JpaRepository<Worker, Integer> {
}
