package com.hastycode.RecapCRUD.service;

import com.hastycode.RecapCRUD.model.Worker;
import com.hastycode.RecapCRUD.repository.WorkerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

//    public Worker addWorker(Worker worker) {
//        return repo.save(worker);
//    }

//    public Worker updateWorker(Worker worker, int id) {
//        Worker updatedWorker = repo.findById(id).orElse(null);
//
//        if(updatedWorker != null) {
//            return repo.save(worker);
//        } else {
//            return null;
//        }
//    }

    public void deleteWorker(int id) {
        repo.deleteById(id);
    }

    public Worker addWorker(Worker worker, MultipartFile imageFile) throws IOException {
        worker.setImageName(imageFile.getOriginalFilename());
        worker.setImageType(imageFile.getContentType());
        worker.setImageData(imageFile.getBytes());
        return repo.save(worker);
    }

    public Worker updateWorker(int workerId, Worker worker, MultipartFile imageFile) throws IOException {
        worker.setImageName(imageFile.getOriginalFilename());
        worker.setImageType(imageFile.getContentType());
        worker.setImageData(imageFile.getBytes());
        return repo.save(worker);
    }
}
