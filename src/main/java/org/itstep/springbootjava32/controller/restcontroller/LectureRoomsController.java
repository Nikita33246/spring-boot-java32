package org.itstep.springbootjava32.controller.restcontroller;

import org.itstep.springbootjava32.model.LectureRooms;
import org.itstep.springbootjava32.service.LectureRoomsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/lecturerooms")
public class LectureRoomController {

    private final LectureRoomsService lectureRoomService;

    @Autowired
    public LectureRoomController(LectureRoomsService lectureRoomsService) {
        this.lectureRoomService = lectureRoomsService;
    }

    // Отображение страницы управления лекционными комнатами
    @GetMapping
    public String showLectureRoomsPage(Model model) {
        model.addAttribute("lectureRooms", lectureRoomService.getAllLectureRooms());
        model.addAttribute("lectureRoom", new LectureRooms());
        return "lectureroom";
    }

    // Добавление/обновление лекционной комнаты
    @PostMapping("/add")
    public String addOrUpdateLectureRoom(@ModelAttribute LectureRooms lectureRoom) {
        lectureRoomService.addLectureRoom(lectureRoom);
        return "redirect:/lecturerooms";
    }

    // Удаление лекционной комнаты
    @PostMapping("/delete/{id}")
    public String deleteLectureRoom(@PathVariable Long id) {
        lectureRoomService.deleteLectureRoom(id);
        return "redirect:/lecturerooms";
    }
}

