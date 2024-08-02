package org.itstep.springbootjava32.email.test;

import org.itstep.springbootjava32.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class TestMailSender {

    private JavaMailSender mailSender;

    @Autowired
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

public  void sendTestMessage(Student student) {
    String message = "Hello " + student.getName() + " from java !";

    SimpleMailMessage email = new SimpleMailMessage();
    email.setTo("46program@gmail.com");
    email.setFrom("46program@ukr.net");
    email.setSubject("Test mail");
    email.setText(message);

    mailSender.send(email);
}


}
