package es.deusto.ingenieria.sd.strava.client.gui;

import java.util.Date;

import es.deusto.ingenieria.sd.strava.client.controller.LoginController;

public class LoginDialog {	
	private LoginController controller;	//TODO INTERFAZ GRAFICA

	public LoginDialog(LoginController controller) {
		
		this.controller = controller;

	}

	
	public boolean login(int i,String mail, String password) {
		System.out.println(" - Login into the server: '" + mail + "' - '" + password + "' ...");
		String sha1 = org.apache.commons.codec.digest.DigestUtils.sha1Hex(password);
		System.out.println("\t* Password hash: " + sha1);	
		switch(i) {
		case 1:
			System.out.println("\t* Login with mail");	
			break;
		case 2:
			System.out.println("\t* Login with google");	
			break;
		case 3:
			System.out.println("\t* Login with facebook");	
		}
		boolean result = this.controller.login(i, mail, sha1); // 1 mail, 2 google, 3 facebook
		System.out.println("\t* Login result: " + result);
		System.out.println("\t* Token: " + this.controller.getToken());
		return result;
	}

	public boolean check(int i,String mail, String password) {
		System.out.println(" - Checking meta account: '" + mail + "' - '" + password + "' ...");
		String sha1 = org.apache.commons.codec.digest.DigestUtils.sha1Hex(password);
		System.out.println("\t* Password hash: " + sha1);	
		
		boolean result = this.controller.check(i,mail, sha1); 
		System.out.println("\t* Login result: " + result);
		return result;
	}
	
	public void logout() {
		System.out.println(" - Logout from the server...");		
		this.controller.logout();
		System.out.println("\t* Logout success!");		
	}

	public boolean register(int i,String mail, String password, String name, Date bday, double weight, int height, int hrMax, int hrMin ) {
		System.out.println(" - Registering to the server...");		// 1 mail, 2 sn
		switch(i) {
		case 1:
			System.out.println("\t* Registering with mail");	
			break;
		case 2:
			System.out.println("\t* Registering with social network");	
			break;
		}
		String sha2 = org.apache.commons.codec.digest.DigestUtils.sha1Hex(password);
		boolean result = this.controller.register(i,mail,name,sha2,bday,weight,height,hrMax,hrMin);
		return result;
	}

	public long getToken() {
		return this.controller.getToken();
	}


}