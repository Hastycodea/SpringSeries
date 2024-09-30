package com.hastycode.RecapCRUD.service;

import com.hastycode.RecapCRUD.model.Worker;
import com.hastycode.RecapCRUD.repository.WorkerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerService {

    private final WorkerRepo repo;

    public WorkerService(WorkerRepo repo) {
        this.repo = repo;
    }


    public List<Worker> getAllWorkers() {
        return repo.findAll();
    }

    public Worker getWorkerById(int id) {
        return repo.findById(id).orElse(null);
    }

    public Worker addWorker(Worker worker) {
        return repo.save(worker);
    }

    public Worker updateWorker(Worker worker) {
        return repo.save(worker);
    }

    public void deleteWorker(int id) {
        repo.deleteById(id);
    }
}
