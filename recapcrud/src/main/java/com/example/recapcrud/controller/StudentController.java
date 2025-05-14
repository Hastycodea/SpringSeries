package com.example.recapcrud.controller;

import com.example.recapcrud.model.Student;
import com.example.recapcrud.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    public StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

//    @RequestMapping("/")
//    public String index() {
//        return "index.html";
//    }
//    @GetMapping("/home")
//    public String home() {
//        return "Welcome Home!";
//    }
    @GetMapping("/test")
    public String test() {
        return "Welcome Home!";
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        return new ResponseEntity<>(service.getAllStudents(), HttpStatus.OK);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        Student student = service.getStudentById(id);
        if(student != null) {
            return new ResponseEntity<>(student, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/students")
    public ResponseEntity<Student> addStudent(@RequestBody Student student){
        Student newStudent = null;
        newStudent = service.addStudent(student);

        if(newStudent != null) {
            return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/student/{id}")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable int id) {
        Student newStudent = service.getStudentById(id);

        if(newStudent != null) {
            return new ResponseEntity<>(service.updateStudent(student), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id){
        Student student = service.getStudentById(id);

        if(student != null) {
            service.deleteStudent(id);
            return new ResponseEntity<>("Student deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Student not found!", HttpStatus.NOT_FOUND);
        }

    }

}
