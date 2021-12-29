package es.deusto.ingenieria.sd.strava.server.serviceAuthorization;


import es.deusto.ingenieria.sd.strava.server.gateway.service.FacebookServiceGateway;
import es.deusto.ingenieria.sd.strava.server.gateway.service.GoogleServiceGateway;
import es.deusto.ingenieria.sd.strava.server.gateway.service.IServiceGateway;

//This interface defines the API of the Server. It represents the Remote Facade pattern
public class ServiceGatewayFactory {	
	public static IServiceGateway createServiceGateway(String[] args, int mode) {
		
		if(mode == 3) {
			return FacebookServiceGateway.getInstance(args[3], Integer.parseInt(args[4]), args[5]);
		}
		else if(mode == 2) {
			return GoogleServiceGateway.getInstance(args[6], Integer.parseInt(args[7]), args[8]);
		}
		else {
			return null;
		}
	}

}