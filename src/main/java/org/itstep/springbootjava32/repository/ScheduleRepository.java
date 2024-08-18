package org.itstep.springbootjava32.repository;

import org.itstep.springbootjava32.service.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    // Поиск расписания по аудитории
    Schedule findByLectureRoomId(Long lectureRoomId);

    // Поиск расписаний, содержащих определенное количество лекций
    List<Schedule> findByLecturesCount(Integer count);

    // Поиск расписания по дате (если добавить поле `date` в `Schedule`)
    List<Schedule> findByDate(LocalDate date);
}

