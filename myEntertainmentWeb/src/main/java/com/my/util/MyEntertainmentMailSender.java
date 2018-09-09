package com.my.util;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.my.dto.EntertainmentMailObject;
import com.my.model.MemberDetails;

public class MyEntertainmentMailSender implements Runnable{
	
	private JavaMailSender sender;
	private EntertainmentMailObject mailObject;
	
	
	public MyEntertainmentMailSender(JavaMailSender sender,EntertainmentMailObject mailObject) {
		this.mailObject=mailObject;
		this.sender=sender;
	}

	@Override
	public void run() {
		try {
			sendMail(sender, mailObject);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	 public void sendMail(JavaMailSender sender,EntertainmentMailObject mailObject) throws AddressException, MessagingException {
	        MimeMessage message = sender.createMimeMessage();
	        message.setFrom(new InternetAddress("myentertainment.mjunction.co.in"));
	        MimeMessageHelper helper = new MimeMessageHelper(message);

	        if(null!=mailObject && null!=mailObject.getMemberDetails() && !mailObject.getMemberDetails().isEmpty()) {
	        	
	        	for (MemberDetails memberDetails : mailObject.getMemberDetails()) {
	        		try {
	    	            helper.setTo(memberDetails.getEmailId());
	    	            helper.setText(mailObject.getMailBody());
	    	            helper.setSubject(mailObject.getMailSubject());
	    	        } catch (MessagingException e) {
	    	            e.printStackTrace();
	    	        }
	    	        sender.send(message);
				}
	        	
	        }
	        
	    }

}
