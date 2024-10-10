package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }


    public void addStudent(Student student) {
        Optional<Student> studentOptionalByEmail = studentRepository.findByEmail(student.getEmail());
        if (studentOptionalByEmail.isPresent()) {
            throw new IllegalArgumentException("Email is already in use");
        }
        studentRepository.save(student);

    }

    public void deleteStudent(Long id) {
        boolean isExist = studentRepository.existsById(id);
        if (!isExist) {
            throw new IllegalArgumentException("Student with id: " + id +" does not exist");
        }
        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long id,String name, String email) {
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Student with id: " + id + " does not exist"));

        if (!name.isEmpty() && !student.getName().equals(name)) {
            student.setName(name);
        }
        if (email != null && !email.isEmpty()) {
            Optional <Student> emailToCheck = studentRepository.findByEmail(email);
            if (emailToCheck.isPresent()) {
                throw new IllegalArgumentException("Email is already in use");
            }
            student.setEmail(email);
        }
    }
}
