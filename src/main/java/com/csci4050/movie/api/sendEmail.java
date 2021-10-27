package com.csci4050.movie.api;


import com.csci4050.movie.api.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.mail.SimpleMailMessage;

public abstract class sendEmail {
    @Autowired
    private JavaMailSender javaMailSender;
	
	public void sendEditEmail(@RequestBody Customer user) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("pidgeontheatres@gmail.com");
        msg.setTo(user.getEmail());
        msg.setSubject("Pigeon Theathers Profile Change");
        msg.setText("Your new profile information is: \n"
                    + "Email: " + user.getEmail()
                    + "\nFirst Name: " + user.getFirstName()
                    + "\nLast Name: " + user.getLastName()
                    + "\nAddress: " + user.getStreet()
                    + "\n\t" + user.getCity() + ", " + user.getState() + " " + user.getZip());
        javaMailSender.send(msg);
    }
}