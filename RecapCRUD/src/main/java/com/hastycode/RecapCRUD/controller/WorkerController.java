package com.hastycode.RecapCRUD.controller;

import com.hastycode.RecapCRUD.model.Worker;
import com.hastycode.RecapCRUD.service.WorkerService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WorkerController {

    private final WorkerService service;

    public WorkerController(WorkerService service) {
        this.service = service;
    }

    @GetMapping("/test")
    public String test() {
        return "Test is running!";
    }

    @GetMapping("/workers")
    public ResponseEntity<List<Worker>> getAllWorkers() {
        return new ResponseEntity<>(service.getAllWorkers(), HttpStatus.OK);
    }

    @GetMapping("/workers/{id}")
    public ResponseEntity<Worker> getWorkerById(@PathVariable int id) {
        Worker worker = service.getWorkerById(id);
        if(worker != null) {
            return new ResponseEntity<>(worker, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/workers")
    public ResponseEntity<Worker> addWorker(@RequestBody Worker worker) {
        if(worker != null) {
            return new ResponseEntity<>(service.addWorker(worker), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/workers/{id}")
    public ResponseEntity<Worker> updateWorker(@PathVariable int id, @RequestBody Worker worker) {
        Worker newWorker = null;
        newWorker = service.updateWorker(worker);

        if(newWorker != null) {
            return  new ResponseEntity<>(newWorker, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/workers/{id}")
    public ResponseEntity<String> deleteWorker(@PathVariable int id) {
        Worker worker = getWorkerById(id).getBody();

        if(worker != null) {
            service.deleteWorker(id);
            return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Worker not found!", HttpStatus.NOT_FOUND);
        }
    }
}
