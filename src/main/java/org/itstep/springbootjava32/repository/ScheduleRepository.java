package org.itstep.springbootjava32.repository;

import org.itstep.springbootjava32.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    // Получение всех записей по дню недели
    List<Schedule> findByDayOfWeek(DayOfWeek dayOfWeek);

    // 3апрос для получения расписания по неделе и дню недели
    @Query("SELECT s FROM Schedule s WHERE s.week = :week AND s.dayOfWeek = :dayOfWeek")
    List<Schedule> findSchedulesByWeekAndDayOfWeek(@Param("week") int week, @Param("dayOfWeek") DayOfWeek dayOfWeek);

    // Получение всех записей по ID лекционной комнаты
    List<Schedule> findByLectureRoom_Id(Long lectureRoomId);
}

