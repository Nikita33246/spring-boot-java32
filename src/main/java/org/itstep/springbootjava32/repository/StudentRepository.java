package org.itstep.springbootjava32.repository;


import org.itstep.springbootjava32.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {


    Student findByName(String name);

    Student findStudentByRating(int rating);

    @Query("select s from Student s order by s.rating desc")
    List<Student> sortStudentByRating();

    @Query("select s from Student s order by s.name asc")
    List<Student> sortStudentByName();

    @Override
    Page<Student> findAll(Pageable pageable);
}
