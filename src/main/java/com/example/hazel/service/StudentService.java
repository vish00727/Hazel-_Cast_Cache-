package com.example.hazel.service;

import com.example.hazel.dto.StudentDto;
import com.example.hazel.entity.Student;
import org.springframework.http.ResponseEntity;

public interface StudentService {

    ResponseEntity<?>save(StudentDto studentDto);

    ResponseEntity<?>get(Long id);

    ResponseEntity<?>update(Long id, StudentDto studentDto);

    void delete(Long id);

}
