package com.aditya.StudentManagementApp.service;

import com.example.studentmanagement.model.Student;

import java.util.List;

public interface StudentService {
    Student addStudent(Student student);
    List<Student> getAllStudents();
    List<Student> searchStudentsByName(String name);
    Student updateStudent(Long id, Student updatedStudent);
    void deleteStudent(Long id);
}
