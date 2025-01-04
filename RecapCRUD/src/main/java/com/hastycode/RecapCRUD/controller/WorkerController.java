package com.hastycode.RecapCRUD.controller;

import com.hastycode.RecapCRUD.model.Worker;
import com.hastycode.RecapCRUD.service.WorkerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
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

//    @PostMapping("/workers")
//    public ResponseEntity<Worker> addWorker(@RequestBody Worker worker) {
//        if(worker != null) {
//            return new ResponseEntity<>(service.addWorker(worker), HttpStatus.OK);
//        }
//        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//    }

    @PostMapping("/workers")
    public ResponseEntity<?> addWorker(@RequestPart Worker worker, @RequestPart MultipartFile imageFile) {
        try {
            Worker worker1 = service.addWorker(worker, imageFile);
            return new ResponseEntity<>(worker1, HttpStatus.CREATED);
        } catch (Exception e) {
           return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/worker/{workerId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int workerId) {
        Worker worker = service.getWorkerById(workerId);
        byte[] imageFile = worker.getImageData();

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(worker.getImageType()))
                .body(imageFile);
    }

//    @PutMapping("/workers/{id}")
//    public ResponseEntity<Worker> updateWorker(@PathVariable int id, @RequestBody Worker worker) {
//        Worker newWorker = null;
//        newWorker = service.updateWorker(worker, id);
//
//        if(newWorker != null) {
//            return  new ResponseEntity<>(newWorker, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
//    }

    @PutMapping("/worker/{workerId}")
    public ResponseEntity<String> updateWorker(@PathVariable int workerId, @RequestPart Worker worker, @RequestPart MultipartFile imageFile) {
        Worker worker1 = null;

        try{
            worker1 = service.updateWorker(workerId, worker, imageFile);
        } catch (Exception e) {
            return  new ResponseEntity<>("Worker not Updated", HttpStatus.BAD_REQUEST); }
        if(worker1 != null) {
            return new ResponseEntity<>("Updated Successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to Update", HttpStatus.BAD_REQUEST );
        }
    }

    @DeleteMapping("/workers/{id}")
    public ResponseEntity<String> deleteWorker(@PathVariable int id) {
        Worker worker = service.getWorkerById(id);

        if(worker != null) {
            service.deleteWorker(id);
            return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Worker not found!", HttpStatus.NOT_FOUND);
        }
    }
}
