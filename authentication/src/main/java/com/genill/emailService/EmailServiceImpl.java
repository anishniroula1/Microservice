/*
package com.genill.emailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl {
	  


		@Autowired
	    public JavaMailSender emailSender;
	 
	    public void sendSimpleMessage(String to, String username) {
	    
	        SimpleMailMessage message = new SimpleMailMessage(); 
	        message.setTo(to); 
	        message.setSubject("Youve been registered"); 
	        message.setText("You have been signed up " +'\n' +'\n' + "Your username is: " + username + "\n Please Login using your password");
	        emailSender.send(message);
	        
	}
}
*/
