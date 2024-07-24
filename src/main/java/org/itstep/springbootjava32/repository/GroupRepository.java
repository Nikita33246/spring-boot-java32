package org.itstep.springbootjava32.repository;


import org.itstep.springbootjava32.model.Group;
import org.itstep.springbootjava32.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    List<Group> findByIdAndYear(int id, int year);

    @Query("select g from Group g join g.students s where s.name = ?1")
    Group findByStudentName(String studentName);

}
