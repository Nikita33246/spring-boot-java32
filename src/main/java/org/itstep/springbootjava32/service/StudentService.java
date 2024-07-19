package org.itstep.springbootjava32.service;

import org.itstep.springbootjava32.model.Student;
import org.itstep.springbootjava32.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public void save(Student student) {
        if (student != null) {
            studentRepository.save(student);
        }
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findById(int id) {
        return studentRepository.findById(id).get();
    }

    public void delete(Student student) {
        studentRepository.delete(student);
    }


}
