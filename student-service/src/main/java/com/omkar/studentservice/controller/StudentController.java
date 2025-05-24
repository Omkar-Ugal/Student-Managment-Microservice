package com.omkar.studentservice.controller;

import com.omkar.studentservice.model.student;
import com.omkar.studentservice.repository.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentRepository studentRepo;

    public StudentController(StudentRepository studentRepo) {
        this.studentRepo = studentRepo;
    }

    @GetMapping
    public List<student> getAllStudents() {
        return studentRepo.findAll();
    }

    @PostMapping
    public student createStudent(@RequestBody student student) {
        return studentRepo.save(student);
    }

    @GetMapping("/{id}")
    public ResponseEntity<student> getStudentById(@PathVariable Long id) {
        return studentRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<student> updateStudent(@PathVariable Long id, @RequestBody student studentDetails) {
        return studentRepo.findById(id)
                .map(student -> {
                    student.setName(studentDetails.getName());
                    student.setEmail(studentDetails.getEmail());
                    return ResponseEntity.ok(studentRepo.save(student));
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        if (!studentRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        studentRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
