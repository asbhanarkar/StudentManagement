package com.aditya.StudentManagementApp.controller;


import com.aditya.StudentManagementApp.shared.Constants;
import com.aditya.StudentManagementApp.shared.dto.Student;
import com.aditya.StudentManagementApp.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;


    @GetMapping("/")
    public ResponseEntity<List<Student>> getAllStudents() {

        List<Student> students=  studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Student>> searchStudents(@RequestParam String name) {

        List<Student> students= studentService.searchStudentsByName(name);
        return ResponseEntity.ok(students);
    }

    @PostMapping("/add")
    public ResponseEntity<Student> addStudent(@Valid @RequestBody Student student) {

        Student std = studentService.addStudent(student);
        return ResponseEntity.ok(std);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @Valid @RequestBody Student student) {

        Student std = studentService.updateStudent(id, student);
        return ResponseEntity.ok(std);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {

        studentService.deleteStudent(id);
        return ResponseEntity.ok(Constants.STUDENT_DELETED_SUCCESSFULLY);
    }
}
