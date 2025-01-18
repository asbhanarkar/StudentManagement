package com.aditya.StudentManagementApp.service;


import com.aditya.StudentManagementApp.shared.dto.Student;
import com.aditya.StudentManagementApp.repository.StudentRepository;
import com.aditya.StudentManagementApp.shared.exception.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;


    @Override
//    @CachePut(value = "students", key = "#result.id")
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
//    @Cacheable(value = "students")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> searchStudentsByName(String name) {
        return studentRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public Student updateStudent(Long id, Student updatedStudent) {
        return studentRepository.findById(id).map(student -> {
            student.setName(updatedStudent.getName());
            student.setAge(updatedStudent.getAge());
            student.setStudentClass(updatedStudent.getStudentClass());
            student.setPhoneNumber(updatedStudent.getPhoneNumber());
            return studentRepository.save(student);
        }).orElseThrow(() -> new StudentNotFoundException("Student with ID "+ id +" not found"));
    }

    @Override
//    @CacheEvict(value = "students", key = "#id")
    public void deleteStudent(Long id) {

        if(!studentRepository.existsById(id)){
            throw new StudentNotFoundException("Student with ID "+ id +" not found");
        }
        studentRepository.deleteById(id);
    }
}
