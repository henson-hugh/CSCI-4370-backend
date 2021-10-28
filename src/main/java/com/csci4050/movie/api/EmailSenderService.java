package com.csci4050.movie.api;

import com.csci4050.movie.api.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeMessage;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendConfirmationEmail(Customer customer, String toEmail){

        SimpleMailMessage message = new SimpleMailMessage();

        String body = "Your account has been created! \n"
                + "Email: " + customer.getEmail()
                + "\nFirst Name: " + customer.getFirstName()
                + "\nLast Name: " + customer.getLastName()
                + "\nAddress: " + customer.getStreet() + " " + customer.getCity()
                + ", " + customer.getState() + " " + customer.getZip()
                + "\n\nVerify your account: "
                + "http://localhost:8080/verify?code=" + customer.getVcode()
                + "&id=" + customer.getCid();
        String subject = "Registration Confirmation";


        message.setFrom("pidgeontheatres@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);
    }

    public void sendEditEmail(Customer customer) {

        SimpleMailMessage message = new SimpleMailMessage();

        String body = "Your account has been changed! \n"
                    + "Email: " + customer.getEmail()
                    + "\nFirst Name: " + customer.getFirstName()
                    + "\nLast Name: " + customer.getLastName()
                    + "\nAddress: " + customer.getStreet() + " " + customer.getCity()
                    + ", " + customer.getState() + " " + customer.getZip();
        String subject = "Registration Confirmation";

        message.setFrom("pidgeontheatres@gmail.com");
        message.setTo(customer.getEmail());
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);
    }

    public void sendForgotEmail(String email, Customer customer) {
        SimpleMailMessage message = new SimpleMailMessage();

        String body = "Click here to reset your email: http://localhost:8080/reset?id=" + customer.getCid();
        String subject = "Reset Password";

        message.setFrom("pidgeontheatres@gmail.com");
        message.setTo(email);
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);
    }
}
