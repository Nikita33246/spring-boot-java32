package org.itstep.springbootjava32.controller;

import org.itstep.springbootjava32.email.registerToken.RegisterToken;
import org.itstep.springbootjava32.email.test.TestMailSender;
import org.itstep.springbootjava32.model.VerificationToken;
import org.itstep.springbootjava32.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private RegisterToken registerToken;



    @Autowired
    public void setRegisterToken(RegisterToken registerToken) {
        this.registerToken = registerToken;
    }

    @GetMapping("/invite/student")
    public String inviteStudent() {
        return "admin/student";
    }


    @GetMapping("/invite/teacher")
    public String inviteTeacher() {
        return "admin/teacher";
    }


    @PostMapping("/invite/student")
    public String inviteStudent(@RequestParam(name = "name") String name,
                                @RequestParam(name = "surname") String surname,
                                @RequestParam(name = "email") String email) {
        registerToken.sendRegistrationToken(name, surname, email);
        return "redirect:/";
    }



}
