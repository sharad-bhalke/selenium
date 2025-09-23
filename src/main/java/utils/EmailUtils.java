package utils;

import java.io.File;
import java.util.Properties;

import base.BaseTest;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

public class EmailUtils extends BaseTest{
	

	 public static void main(String[] args) {

	        final String senderEmail = "sharad.bhalke7@gmail.com";
	        final String appPassword = "lrsudwblcfwbhrsm"; // Gmail App Password
	        final String recipientEmail = "sharad.bhalke7@gmail.com, sharad.bhalke9521@gmail.com";

	        // SMTP Server Properties
	        Properties prop = new Properties();
	        prop.put("mail.smtp.auth", "true");
	        prop.put("mail.smtp.starttls.enable", "true");
	        prop.put("mail.smtp.host", "smtp.gmail.com");
	        prop.put("mail.smtp.port", "587");
	       // prop.put("mail.smtp.ssl.trust", "smtp.gmail.com"); // Important
	        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");



	        // Create session
	        Session session = Session.getInstance(prop, new Authenticator() {
	            @Override
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(senderEmail, appPassword);
	            }
	        });

	        session.setDebug(true); // Enable debug logs

	        try {
	            // Create email message
	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress(senderEmail));
	            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
	            message.setSubject("Test email from QA team");
	            message.setText("Hello,\n\nThis is a test email from Java.\n\nRegards,\nQA Team");
	            
	            //email body part
	            MimeBodyPart textpart=new MimeBodyPart();
	            textpart.setText("Hello,\n\nThis is a test email from Java.\n\nRegards,\nQA Team");
	            
	         // Attachment part
	            MimeBodyPart attachmentPart = new MimeBodyPart();

	            // Correct property for project directory
	            String filePath = System.getProperty("user.dir") + "/reports/ExtentReport.html";
	            System.out.println("Attached file path: " + filePath);

	            // Attach file
	            attachmentPart.attachFile(new File(filePath));
	            
	            //combine email and body part and attachement
	            
	            MimeMultipart multipart=new MimeMultipart();
	            multipart.addBodyPart(textpart);
	            multipart.addBodyPart(attachmentPart);
	            message.setContent(multipart);

	            
	            
	           // AttachementPart.

	            // Send email
	           Transport.send(message);
	           System.out.println(" Mail sent successfully!");

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}

