package org.itstep.springbootjava32.repository;

import org.itstep.springbootjava32.model.LectureRooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureRoomsRepository extends JpaRepository<LectureRooms, Long> {

    // Получение всех лекционных комнат по названию здания
    List<LectureRooms> findByBuilding(String building);

    // 3апрос для получения комнат по части названия
    @Query("SELECT lr FROM LectureRooms lr WHERE lr.name LIKE %:namePart%")
    List<LectureRooms> findRoomsByNamePart(String namePart);
}

