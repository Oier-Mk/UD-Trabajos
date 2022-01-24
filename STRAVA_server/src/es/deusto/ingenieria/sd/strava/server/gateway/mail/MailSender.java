package es.deusto.ingenieria.sd.strava.server.gateway.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class MailSender {
	private final String subject = "Testing the Java MailSender: Sending a message by email ...";
	private Properties props;

	private static MailSender instance;	

	private MailSender() {
		props = new Properties();
		try {
			props.load(MailSender.class.getClassLoader().getResourceAsStream("mail.properties"));
		} catch (Exception e) {
			System.out.println(" % Error loading mailsender.properties file: "+ e.getMessage());
		}
		
	}
	
	public static MailSender getInstance() {
		if (instance == null) {
			instance = new MailSender();
		}		
		System.out.println(instance.toString());
		return instance;
	}	
	
	public String sendMessage(String to, String text) {
		try {
			Authenticator auth = new SMTPAuthenticator();
			Session session = Session.getInstance(props, auth);
			session.setDebug(true);

			MimeMessage msg = new MimeMessage(session);
			msg.setText(text.trim());
			msg.setSubject(subject);
			msg.setFrom(new InternetAddress(props.getProperty("mail.smtp.user")));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			Transport.send(msg);
			System.out.println("Message sent to: " + to);
		} catch (Exception ex) {
			System.err.println(" $ Error sending message: " + ex);
		}
		return "OK";
	}

	private class SMTPAuthenticator extends javax.mail.Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(props.getProperty("mail.smtp.user"), props.getProperty("mail.smtp.password"));
		}
	}

	@Override
	public String toString() {
		return "MailSender [sender=" + props.getProperty("mail.smtp.user") + ", password=" + props.getProperty("mail.smtp.password") + "]";
	}
	
	
}