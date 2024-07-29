package org.itstep.springbootjava32.repository;

import org.itstep.springbootjava32.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {


    @Query("select dp from Group g join g.department dp join g.students s where s.name = ?1")
    Department findByStudentName(String studentName);


}
