package com.omkar.studentservice.repository;

import com.omkar.studentservice.model.student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<student,Long> {

}
