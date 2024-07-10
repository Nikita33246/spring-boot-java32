package org.itstep.springbootjava32.controller;

import org.itstep.springbootjava32.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("v1")
public class MyController {

    List<Student> studentList = new ArrayList<>();

    {
        studentList.add(new Student(1,"Jack", "jack@", "345345"));
        studentList.add(new Student(2,"Jack1", "jack1@", "345345"));
        studentList.add(new Student(3,"Jack2", "jack2@", "345345"));
    }


//    @GetMapping("/hello")
//    public String hello() {
//       return "Hello from controller";
//    }


    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/model")
    public String modelEx(Model model) {
        model.addAttribute("my_msg", "Hello from controller");
        return "home";
    }

    @GetMapping("/path/{name}")
    public String paramEx(@PathVariable String name, Model model) {
        model.addAttribute("myName", name);
        return "home";
    }


    @GetMapping("/student")
    public String form(Model model) {
        model.addAttribute("myStudent", new Student());
        return "student";
    }


    @PostMapping("/student")
    public String form(@ModelAttribute Student student, Model model) {
        model.addAttribute("myName", student.getName());
        model.addAttribute("myEmail", student.getEmail());
        model.addAttribute("myPhone", student.getPhone());
        studentList.add(student);
        return "show-student";
    }

    @GetMapping("/show-students")
    public String showStudents(Model model) {
        model.addAttribute("allStudents", studentList);
        return "all-students";
    }


    @GetMapping("/show-student/{id}")
    public String showStudent(@PathVariable(value = "id") String id, Model model) {
        System.out.println("==============================");
        Student student = new Student();
        for (Student student1 : studentList) {
            if (student1.getId() == Integer.parseInt(id)) {
                student = student1;
                break;
            }
        }
        model.addAttribute("myName", student.getName());
        model.addAttribute("myEmail", student.getEmail());
        model.addAttribute("myPhone", student.getPhone());
        return "show-student";
    }

}
