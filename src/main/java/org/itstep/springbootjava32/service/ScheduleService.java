package org.itstep.springbootjava32.service;

import org.itstep.springbootjava32.model.Schedule;
import org.itstep.springbootjava32.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public Schedule getScheduleById(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
    }

    public List<Schedule> getSchedulesByDayOfWeek(DayOfWeek dayOfWeek) {
        return scheduleRepository.findByDayOfWeek(dayOfWeek);
    }

    public List<Schedule> getSchedulesByWeekAndDayOfWeek(int week, DayOfWeek dayOfWeek) {
        return scheduleRepository.findSchedulesByWeekAndDayOfWeek(week, dayOfWeek);
    }

    public Schedule addSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public Schedule updateSchedule(Long id, Schedule updatedSchedule) {
        Schedule existingSchedule = getScheduleById(id);
        existingSchedule.setClassName(updatedSchedule.getClassName());
        existingSchedule.setDayOfWeek(updatedSchedule.getDayOfWeek());
        existingSchedule.setWeek(updatedSchedule.getWeek());
        existingSchedule.setLecture(updatedSchedule.getLecture());
        existingSchedule.setLectureRoom(updatedSchedule.getLectureRoom());
        return scheduleRepository.save(existingSchedule);
    }

    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }
}

