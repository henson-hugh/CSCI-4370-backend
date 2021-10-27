package com.csci4050.movie.api;

import com.csci4050.movie.api.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendConfirmationEmail(Customer customer, String toEmail) {


        SimpleMailMessage message = new SimpleMailMessage();

        String body = "Your account has been created! \n"
                + "Email: " + customer.getEmail()
                + "\nFirst Name: " + customer.getFirstName()
                + "\nLast Name: " + customer.getLastName()
                + "\nAddress: " + customer.getAddress()
                + "\n\nVerify your account: "
                + "http://localhost:8080/verify?code=" + customer.getVerificationCode();
        String subject = "Registration Confirmation";


        message.setFrom("pidgeontheatres@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);
    }

        public void sendEditEmail(Customer customer) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("pidgeontheatres@gmail.com");
        msg.setTo(customer.getEmail());
        msg.setSubject("Pigeon Theathers Profile Change");
        msg.setText("Your new profile information is: \n"
                + "Email: " + customer.getEmail()
                + "\nFirst Name: " + customer.getFirstName()
                + "\nLast Name: " + customer.getLastName()
                + "\nAddress: " + customer.getAddress());
            mailSender.send(msg);
    }
}
