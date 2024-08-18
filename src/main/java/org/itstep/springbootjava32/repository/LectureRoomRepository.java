package org.itstep.springbootjava32.repository;

import org.itstep.springbootjava32.service.LectureRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureRoomRepository extends JpaRepository<LectureRoom, Long> {

    // Поиск аудитории по названию
    LectureRoom findByName(String name);

    // Поиск всех аудиторий с определенной вместимостью или большей
    List<LectureRoom> findByCapacityGreaterThanEqual(Integer capacity);
}

