package org.itstep.springbootjava32.repository;

import org.itstep.springbootjava32.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {

    Image findByStudentId(Integer id);

}
