package es.deusto.ingenieria.sd.strava.server;

import es.deusto.ingenieria.sd.strava.server.gateway.mail.MailSender;

public class MainMail {

	public static void main(String[] args) {
		String receiver = "oiermentxaka@opendeusto.es";
		String message = "This is a testing message that sends details using Gmail. Useful for Java projects!";
		
		MailSender.getInstance().sendMessage(receiver,message);

	}

}
