package com.hastycode.student_management_system.service;

import com.hastycode.student_management_system.entity.Student;

import java.util.List;

public interface StudentService {
    Student saveStudent(Student student);

    List<Student> getAllStudents();
}
