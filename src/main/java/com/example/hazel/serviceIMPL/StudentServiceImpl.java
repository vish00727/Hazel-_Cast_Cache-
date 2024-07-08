package com.example.hazel.serviceIMPL;

import com.example.hazel.dto.StudentDto;
import com.example.hazel.entity.Student;
import com.example.hazel.repo.StudentRepo;
import com.example.hazel.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Override
    public ResponseEntity<?> save(StudentDto studentDto) {
        Student stud=new Student();
        stud.setName(studentDto.getName());
        stud.setLastName(studentDto.getLastName());
        stud.setAge(studentDto.getAge());
        stud.setMarks(studentDto.getMarks());
        studentRepo.save(stud);
        return  ResponseEntity.ok(stud);
    }

    @Override
    public ResponseEntity<?> get(Long id) {
         Optional<Student> getData=studentRepo.findById(id);
        return ResponseEntity.ok(getData);
    }

    @Override
    public ResponseEntity<?> update(Long id, StudentDto studentDto) {
        Optional<Student> existingStudentOptional = studentRepo.findById(id);
        if (existingStudentOptional.isPresent()) {
            Student existingStudent = existingStudentOptional.get();
            existingStudent.setName(studentDto.getName());
            existingStudent.setLastName(studentDto.getLastName());
            existingStudent.setAge(studentDto.getAge());
            existingStudent.setMarks(studentDto.getMarks());
            studentRepo.save(existingStudent);
            return ResponseEntity.ok(existingStudent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public void delete(Long id) {
        studentRepo.deleteById(id);
    }


}
