package org.itstep.springbootjava32.repository;


import org.itstep.springbootjava32.model.Curator;
import org.itstep.springbootjava32.model.Department;
import org.itstep.springbootjava32.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuratorRepository extends JpaRepository<Curator, Long> {

   @Query("select c from Curator c join c.groups g where g.name = ?1")
   Curator findByGroupName(String groupName);


}
