package org.itstep.springbootjava32.email.registerToken;

import org.itstep.springbootjava32.model.Student;
import org.itstep.springbootjava32.model.User;
import org.itstep.springbootjava32.service.StudentService;
import org.itstep.springbootjava32.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;

@Component
public class RegisterToken {

    private JavaMailSender mailSender;

    private StudentService studentService;

    private UserService userService;


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @Autowired
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendRegistrationToken(String name, String surname, String email) {

        String token = UUID.randomUUID().toString();


        String url = "/student/registerToken?token=" + token;

        String message = "Перейдіть за цим посиланням для підтвердження реєстрації";

        String login = createLoginBySurname(surname);
        //String login = createLoginByEmail(email);
        String password = createPassword(token);

        Student student = new Student(name, surname);
        User user = new User(login, password, email, student);

        studentService.save(student);

        userService.saveStudentUser(user);

        userService.createToken(token, user);

        String msg = "\nВаш логін: " + login + "\n" + "Ваш пароль: " + password;

        SimpleMailMessage simpleMail = new SimpleMailMessage();
        simpleMail.setTo(email);
        simpleMail.setFrom("46program@ukr.net");
        simpleMail.setSubject("Registration Token");
        simpleMail.setText(message + " " + "http://localhost:8080" + url + msg);
        mailSender.send(simpleMail);
    }

    private String createLoginBySurname(String surname) {
        Random random = new Random();
        return surname.toLowerCase() + "_" + random.nextInt(10000);
    }

    private String createPassword(String token) {
        return token.substring(token.length() - 6);
    }


    private String createLoginByEmail(String email) {
        Random random = new Random();
        return email.split("@")[0] + "_" + random.nextInt(10000);
    }


}
