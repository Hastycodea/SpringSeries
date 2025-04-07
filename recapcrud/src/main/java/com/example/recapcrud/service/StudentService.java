package com.example.recapcrud.service;

import com.example.recapcrud.model.Student;
import com.example.recapcrud.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    public StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    public Student getStudentById(int id) {
        return repo.findById(id).orElse(null);
    }

    public Student addStudent(Student student) {
        return repo.save(student);
    }

    public Student updateStudent(Student student) {
        return repo.save(student);
    }

    public void deleteStudent(int id) {
        repo.deleteById(id);
    }
}
