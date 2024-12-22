package com.hastycode.checkmates.controller;

import com.hastycode.checkmates.model.Checkmate;
import com.hastycode.checkmates.service.CheckmateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CheckmateController {

    private final CheckmateService service;

    public CheckmateController(CheckmateService service) {
        this.service = service;
    }

    @GetMapping("/home")
    public String test() {
        return "Welcome to home";
    }

    @GetMapping("/checkmates")
    public ResponseEntity<List<Checkmate>> getAllCheckmates() {
        return new ResponseEntity<>(service.getAllCheckmates(), HttpStatus.OK);
    }

    @GetMapping("/checkmate/{id}")
    public ResponseEntity<Checkmate> getCheckmateById(@PathVariable long id) {
        Checkmate checkmate = service.getCheckmateById(id);
        if(checkmate != null) {
            return new ResponseEntity<>(checkmate, HttpStatus.OK);
        }
        return  new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/checkmates")
    public ResponseEntity<Checkmate> addCheckmate(@RequestBody Checkmate checkmate) {
        Checkmate newCheckmate = null;
        newCheckmate = service.addCheckmate(checkmate);

        if(newCheckmate != null) {
            return new ResponseEntity<>(newCheckmate, HttpStatus.CREATED);
        } else {
            return  new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/checkmates/{id}")
    public ResponseEntity<Checkmate> updateCheckmate(@PathVariable long id, @RequestBody Checkmate checkmate) {
       Checkmate newCheckmate = null;
       newCheckmate = service.updatedCheckmate(id, checkmate);

       if(newCheckmate != null) {
           return new ResponseEntity<>(newCheckmate, HttpStatus.OK);
       } else {
           return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
       }
    }

    @DeleteMapping("/checkmates/{id}")
    public ResponseEntity<String> deleteCheckmate(@PathVariable long id) {
        Checkmate checkmate = service.getCheckmateById(id);
        if(checkmate != null) {
            service.deleteCheckmate(id);
            return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
        }
        else {
            return  new ResponseEntity<>("Not found!", HttpStatus.NOT_FOUND);
        }
    }
}
