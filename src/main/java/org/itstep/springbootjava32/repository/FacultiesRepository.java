package org.itstep.springbootjava32.repository;

import org.itstep.springbootjava32.model.Faculties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultiesRepository extends JpaRepository<Faculties, Integer> {

    @Query("select f from Student s join s.group g join g.department d join d.faculties f where s.name = ?1")
    Faculties findByName(String name);
}
