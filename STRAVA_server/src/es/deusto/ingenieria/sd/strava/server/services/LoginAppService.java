package es.deusto.ingenieria.sd.strava.server.services;

import java.io.IOException;
import java.util.Date;

import es.deusto.ingenieria.sd.strava.server.data.dao.UserDAO;
import es.deusto.ingenieria.sd.strava.server.data.domain.User;
import es.deusto.ingenieria.sd.strava.server.gateway.service.IServiceGateway;
import es.deusto.ingenieria.sd.strava.server.serviceAuthorization.ServiceGatewayFactory;

public class LoginAppService {
	private String[] args;	

	public LoginAppService(String[] args) {
		this.args = args;
	}

	public User login(int i, String mail, String password) throws Exception {
        System.out.println("  #  "+password);

		if(i == 1){
			return loginMail(mail, password);
		}else {
			System.out.println("existe en google o facebook");
			if((ServiceGatewayFactory.createServiceGateway(args,i)).login(mail, password)) {
				return loginMail(mail, password);
			}else {
				return null;
			}
		}
	}

	public static LoginAppService getInstance(String[] args) {
		return new LoginAppService(args);
	}


	private User loginMail(String mail, String password) {
        System.out.println("  #  "+password);

		User user = UserDAO.getInstance().find(mail);
		if(user==null)System.out.println("usuario nulo en el DAO");
		if(user!=null) {
			if(user.getPassword().equals(password)) {
				return user;
			}
			else {System.out.println("password en caso de no cuadrar "+user.getPassword()+"    ±     "+password);}
		} 
		return null;	
	}

	public User register(int i, String mail, String name, String password, Date bday, double weight, int height, int hrMax,
			int hrMin) {
		switch(i) {
		case 1:
			return registerStrava(mail,name,password,bday,weight,height,hrMax,hrMin);
		case 2:
			return registerStrava(mail,name,password,bday,weight,height,hrMax,hrMin);
		case 3:
			return registerStrava(mail,name,password,bday,weight,height,hrMax,hrMin);
		}
		return null;
	}

	private User registerStrava(String mail, String name, String password, Date bday, double weight, int height,
			int hrMax, int hrMin) {
		if(UserDAO.getInstance().find(mail)==null) {
			User u = new User(mail, name, password, bday, weight, height, hrMax, hrMin);
			UserDAO.getInstance().save(u);
			u = UserDAO.getInstance().find(u.getMail());
			return u;
		}	else {
			return null;
		}
	}


	public User getUser(String email) {
		User user = UserDAO.getInstance().find(email);
		return user;
	}


	public boolean check(int i, String email, String password) throws IOException {
		try {
			IServiceGateway a = ServiceGatewayFactory.createServiceGateway(args,i);
			return a.login(email, password);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}