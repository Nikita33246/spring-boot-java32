package org.itstep.springbootjava32.repository;


import org.itstep.springbootjava32.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {


    Student findByName(String name);

    Student findStudentByRating(int rating);

}
