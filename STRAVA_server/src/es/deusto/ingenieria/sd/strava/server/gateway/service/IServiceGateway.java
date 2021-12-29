package es.deusto.ingenieria.sd.strava.server.gateway.service;

public interface IServiceGateway {
	
	public boolean login(String mail, String password) throws Exception;

}
