package org.itstep.springbootjava32.controller.restcontroller;

import org.itstep.springbootjava32.model.Schedule;
import org.itstep.springbootjava32.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping
    public String showSchedulesPage(Model model) {
        model.addAttribute("schedules", scheduleService.getAllSchedules());
        model.addAttribute("schedule", new Schedule());
        return "schedule";
    }

    @PostMapping("/add")
    public String addOrUpdateSchedule(@ModelAttribute Schedule schedule) {
        scheduleService.addSchedule(schedule);
        return "redirect:/schedules";
    }

    @PostMapping("/delete/{id}")
    public String deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteScheduleById(id);
        return "redirect:/schedules";
    }
}

