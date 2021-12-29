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
	private String sender;
	private String password;
	private String host;
	private String port;
	private final String subject = "Testing the Java MailSender: Sending a message by email ...";
	private String to;

	private Properties props;

	public MailSender(String sender, String host, String port) {
		props = new Properties();
		props.put("mail.smtp.user", sender);
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.debug", "false");
	}

	private static MailSender instance;	

	public static MailSender getInstance(String receiverEmail, String sender,String password, String host, String port) {
		if (instance == null) {
			instance = new MailSender(sender,host,port);
			instance.to = receiverEmail;
			instance.sender = sender;
			instance.password = password;
			instance.host = host;
			instance.port = port;
		}		
		System.out.println(instance.toString());

		return instance;
	}	
	
	public String sendMessage(String text) {
		try {
			Authenticator auth = new SMTPAuthenticator();
			Session session = Session.getInstance(props, auth);
			session.setDebug(true);

			MimeMessage msg = new MimeMessage(session);
			msg.setText(text.trim());
			msg.setSubject(subject);
			msg.setFrom(new InternetAddress(sender));
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
			return new PasswordAuthentication(sender, password);
		}
	}

	@Override
	public String toString() {
		return "MailSender [sender=" + sender + ", password=" + password + ", host=" + host + ", port=" + port
				+ ", subject=" + subject + ", to=" + to + ", props=" + props + "]";
	}
	
	
}