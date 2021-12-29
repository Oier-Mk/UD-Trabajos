package es.deusto.ingenieria.sd.strava.server.gateway.service;

import java.rmi.Naming;

import es.deusto.ingenieria.sd.google.server.remote.IGoogleFacade;

public class GoogleServiceGateway implements IServiceGateway {
	private static IServiceGateway instance = null;
	private IGoogleFacade service;
	public GoogleServiceGateway(String serverIP, int serverPort, String serverName) {
		try{
			String URL = "//"+serverIP+":"+serverPort+"/"+serverName;
			this.service = (IGoogleFacade) Naming.lookup(URL);
		}catch (Exception e) {
			e.printStackTrace();
		}

	}
	public static IServiceGateway getInstance(String serverIP,int serverPort, String serverName) {
		if(instance == null) {
			instance = new GoogleServiceGateway(serverIP, serverPort, serverName);
		}
		return instance;
	}
	@Override
	public boolean login(String mail, String password) throws Exception {
		System.out.println("existe en google "+ service.loginGoogle(mail, password));
		return service.loginGoogle(mail, password);
	}
}
