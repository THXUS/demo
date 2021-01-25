package com.example.demo.student;

import java.util.List;
import java.util.Optional;

import org.spark_project.guava.base.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
        if (studentByEmail.isPresent()) {
            throw new IllegalStateException("Email Taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException("student with id " + studentId + " does not exists");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String studentName, String studentEmail) {
        Student estudante = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Esse caboclo não existe"));
        if (studentName != null && studentName.length() > 0 && !Objects.equal(estudante.getName(), studentName)) {
            estudante.setName(studentName);
        }
        if (studentEmail != null && studentEmail.length() > 0 && !Objects.equal(estudante.getEmail(), studentEmail)) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(studentEmail);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("Email Taken");
            }
            estudante.setEmail(studentEmail);
        }

    }
}
