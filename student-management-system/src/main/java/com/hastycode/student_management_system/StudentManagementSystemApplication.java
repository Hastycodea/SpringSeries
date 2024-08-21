package com.hastycode.student_management_system;

import com.hastycode.student_management_system.entity.Student;
import com.hastycode.student_management_system.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentManagementSystemApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementSystemApplication.class, args);
	}

	@Autowired
	private StudentRepository repo;

	@Override
	public void run(String... args) throws Exception {
//		Student student1 = new Student("Andreh", "Andreh", "andreh@gmail.com");
//		repo.save(student1);
//		Student student2 = new Student("Kim", "BodyBuilder", "kim@yahoo.com");
//		repo.save(student2);
//		Student student3 = new Student("Ashley", "Mathenge", "matheri@yahoo.com");
//		repo.save(student3);

	}
}
