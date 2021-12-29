package es.deusto.ingenieria.sd.strava.client.controller;

import java.rmi.RemoteException;
import java.util.Date;

//import es.deusto.ingenieria.sd.strava.client.gui.WelcomeW;
import es.deusto.ingenieria.sd.strava.client.remote.ServiceLocator;

//This class implements Controller pattern.
public class LoginController {	
	
	//Reference to the Service Locator
	private ServiceLocator serviceLocator;
	//This attibute stores the token when login success
	private long token = -1; //-1 = login has not been done or fails
		
	public LoginController(ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}

	public boolean login(int i, String email, String password) {
		try {
			this.token = this.serviceLocator.getService().login(i, email, password);			
			return true;
		} catch (Exception e) {
			System.out.println("# Error during login: "); e.printStackTrace();
			this.token = -1;
			return false;
		}
	}
	
	public boolean register(int i,String mail, String name, String password, Date bday, double weight, int height, int hrMax, int hrMin) {
		try {
			this.token = this.serviceLocator.getService().register(i,mail,name,password,bday,weight,height,hrMax,hrMin);			
			return true;
		} catch (Exception e) {
			System.out.println("# Error during register: ");e.printStackTrace();
			this.token = -1;
			return false;
		}
	}
	
	public boolean check(int i,String mail,String password) {
		try {
			if(this.serviceLocator.getService().check(i,mail,password)) return true;			
			else return false;
		} catch (RemoteException e) {
			System.out.println("# Error during check: ");e.printStackTrace();
			this.token = -1;
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void logout() {
		try {
			this.serviceLocator.getService().logout(this.token);
			this.token = -1;
		} catch (Exception e) {
			System.out.println("# Error during logout: ");e.printStackTrace();
		}
	}

	public long getToken() {
		return token;
	}
}