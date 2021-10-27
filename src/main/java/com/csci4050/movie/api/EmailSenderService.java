package com.csci4050.movie.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendConfirmationEmail(String toEmail) {


        SimpleMailMessage message = new SimpleMailMessage();

        String body = "Your account has been created!";
        String subject = "Registration Confirmation";

        message.setFrom("pidgeontheatres@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);
    }
}
