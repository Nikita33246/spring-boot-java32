package org.itstep.springbootjava32.controller;

import org.itstep.springbootjava32.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("v1")
public class MyController {


//    @GetMapping("/hello")
//    public String hello() {
//       return "Hello from controller";
//    }


    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/model")
    public String modelEx(Model model){
        model.addAttribute("my_msg", "Hello from controller");
        return "home";
    }

    @GetMapping("/path/{name}")
    public String paramEx(@PathVariable String name, Model model){
        model.addAttribute("myName", name);
        return "home";
    }


    @GetMapping("/student")
    public String form(Model model){
        model.addAttribute("myStudent", new Student());
        return "student";
    }



    @PostMapping("/student")
    public String form(@ModelAttribute Student student, Model model){
        model.addAttribute("myName", student.getName());
        model.addAttribute("myEmail", student.getEmail());
        model.addAttribute("myPhone", student.getPhone());
        return "show-student";
    }




}
