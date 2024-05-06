package com.soya;

import java.util.*; 
import javax.mail.*;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.*;
public class SendWebMail {
	
	public String send_mail(String subject, String message,String email_id) {
		String mailStatus="Failed";
		String smtpServer = null; 
                String smtpPort = null;
		final String authAddress = "보내는 사람 이메일";      
		final String authPassword = "앱 비밀번호";     
        smtpServer = "smtp.gmail.com";
        smtpPort = "587";    
        try{    
                Properties props = new Properties(); 
                props.put("mail.smtp.starttls.enable","true");
                props.put("mail.smtp.host", smtpServer); 
                props.put("mail.smtp.port", smtpPort);   
                props.put("mail.smtp.auth", "true");         
                props.put("mail.smtp.ssl.protocols", "TLSv1.2");
                props.put("mail.smtp.ssl.enable", "false");
            // create some properties and get the default Session
                Session sessionMail = Session.getInstance(props, new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(authAddress, authPassword);
                    }
                });                                                              
            sessionMail.setDebug(true);
            // create a message
            Message msg = new MimeMessage(sessionMail);
            // set the from and to address
            InternetAddress addressFrom = new InternetAddress(authAddress);
            msg.setFrom(addressFrom);
            InternetAddress[] addressTo = new InternetAddress[1];
            addressTo[0] = new InternetAddress(email_id);
            msg.setRecipients(Message.RecipientType.TO, addressTo);
            msg.setSubject(subject);
            msg.setContent(message, "text/html");
            Transport.send(msg);
            mailStatus ="Success";
            System.out.println("Mail sent");
        }catch(Exception e){
                System.out.println(e);
        }
        return mailStatus;
	}
}