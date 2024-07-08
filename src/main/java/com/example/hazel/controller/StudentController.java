package com.example.hazel.controller;

import com.example.hazel.dto.StudentDto;
import com.example.hazel.serviceIMPL.StudentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@CacheConfig(cacheNames = "student")
@Slf4j
public class StudentController {
    @Autowired
    private StudentServiceImpl studentService;

    @PostMapping("save")
    private ResponseEntity<?>saveStudent(@RequestBody StudentDto studentDto){
        return studentService.save(studentDto);
    }

    @GetMapping("get/{id}")
    @Cacheable(key = "#id")
    private ResponseEntity<?>get(@PathVariable Long id) throws InterruptedException {
    log.info("fetching the student id"+id+"from DB");
       return studentService.get(id);
    }

    @PutMapping("/update/{id}")
    @CachePut(key = "#id")
    public ResponseEntity<?>update(@PathVariable Long id, @RequestBody StudentDto studentDto){
    log.info("Update the student id"+id+"from DB");
    return studentService.update(id,studentDto);
    }

    @DeleteMapping("/delete/{id}")
    @CacheEvict(key = "#id")
    public void delete(@PathVariable Long id) {
    log.info("Update the student id"+id+"from DB");
    studentService.delete(id);
    }

}
