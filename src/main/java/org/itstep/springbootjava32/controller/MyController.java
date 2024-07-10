package org.itstep.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {


    @GetMapping("/hello")
    public String hello() {
       return "Hello from controller";
    }


    @GetMapping("/home")
    public String home(){
        return "home1";
    }

    @GetMapping("/model")
    public String modelEx(Model model){
        model.addAttribute("msg", "Hello from controller");
        return "home";
    }



}
