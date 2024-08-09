package org.itstep.springbootjava32.service;

import org.itstep.springbootjava32.model.Student;
import org.itstep.springbootjava32.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Student findStudentByName(String name) {
        return studentRepository.findByName(name);
    }

    public List<Student> sortingByValue(String value) {

        if (value == null) {
            return studentRepository.findAll();
        } else if (value.equalsIgnoreCase("name")) {
            return studentRepository.sortStudentByName();
        } else if (value.equalsIgnoreCase("rating")) {
            return studentRepository.sortStudentByRating();
        } else {
            studentRepository.findAll();
        }

        return studentRepository.findAll();
    }

    public Page<Student> findAllStudents(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }
}
