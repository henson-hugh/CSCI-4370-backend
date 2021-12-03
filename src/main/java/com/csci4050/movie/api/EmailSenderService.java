package com.csci4050.movie.api;

import com.csci4050.movie.api.model.*;
import com.csci4050.movie.api.showing.ShowingController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    ShowingController showingController;
    public void sendRegistrationEmail(Customer customer, String toEmail, String code){

        SimpleMailMessage message = new SimpleMailMessage();

        String body = "Your account has been created! \n"
                + "Email: " + toEmail
                + "\nFirst Name: " + customer.getFirstName()
                + "\nLast Name: " + customer.getLastName()
                + "\nAddress: " + customer.getStreet() + " " + customer.getCity()
                + ", " + customer.getState() + " " + customer.getZip()
                + "\n\nVerify your account: "
                + "http://localhost:8080/customer/verify?code=" + code
                + "&id=" + customer.getCid();
        String subject = "Registration Confirmation";


        message.setFrom("pidgeontheatres@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);
    }

//    public void sendEditEmail(Customer customer) {
//
//        SimpleMailMessage message = new SimpleMailMessage();
//
//        String body = "Your account has been changed! \n"
//                    + "Email: " + customer.getEmail()
//                    + "\nFirst Name: " + customer.getFirstName()
//                    + "\nLast Name: " + customer.getLastName()
//                    + "\nAddress: " + customer.getStreet() + ", " + customer.getCity()
//                    + ", " + customer.getState() + " " + customer.getZip()
//                    + "\nPhone: " + customer.getPhone();
//        String subject = "Changes made to Profile";
//
//        message.setFrom("pidgeontheatres@gmail.com");
//        message.setTo(customer.getEmail());
//        message.setText(body);
//        message.setSubject(subject);
//
//        mailSender.send(message);
//    }

    public void sendForgotEmail(String email, User user) {
        SimpleMailMessage message = new SimpleMailMessage();

        String body = "Click here to reset your password: http://localhost:8080/reset?id=" + user.getUid();
        String subject = "Reset Password";

        message.setFrom("pidgeontheatres@gmail.com");
        message.setTo(email);
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);
    }

    public void sendPromoEmail(Promotion promo, User user) {
        SimpleMailMessage message = new SimpleMailMessage();

        String body = "New promotion! \nPromotion code: " + promo.getPcode()
                + "\nAvailable until: " + promo.getEndDate().toString()
                + "\nDiscount: " + (int)Math.round((1 - promo.getDiscount()) * 100) + "% off on your next purchase"
                + "\nDescription: " + promo.getDescription();
        String subject = "New Promotions!";

        message.setFrom("pidgeontheatres@gmail.com");
        message.setText(body);
        message.setSubject(subject);
        message.setTo(user.getEmail());
        mailSender.send(message);
    }

    public void sendBookingEmail(Showing showing, User user){
        SimpleMailMessage message = new SimpleMailMessage();
        Movie movie = showingController.searchMoviesById(showing.getSid()).get();
        String movieTitle = movie.getTitle();
        String body = "You have sucessfully created a new booking to see "
                + movieTitle + "at " + showing.getTime() +"on " + showing.getDate();
        String subject = "New Booking!";
        message.setFrom("pidgeontheatres@gmail.com");
        message.setText(body);
        message.setSubject(subject);
        message.setTo(user.getEmail());
        mailSender.send(message);
    }
}
