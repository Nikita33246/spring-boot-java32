package org.itstep.springbootjava32.repository;

import org.itstep.springbootjava32.service.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {

    // Поиск всех лекций по имени лектора
    List<Lecture> findByLecturer(String lecturer);

    // Поиск всех лекций по теме (игнорируя регистр)
    List<Lecture> findByTopicIgnoreCase(String topic);

    // Поиск всех лекций, связанных с определенным расписанием
    List<Lecture> findByScheduleId(Long scheduleId);
}

