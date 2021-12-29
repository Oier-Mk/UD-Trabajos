package es.deusto.ingenieria.sd.strava.server;

import java.rmi.Naming;
import java.text.SimpleDateFormat;
import java.util.Date;

import es.deusto.ingenieria.sd.strava.server.data.dao.UserDAO;
import es.deusto.ingenieria.sd.strava.server.data.domain.User;
import es.deusto.ingenieria.sd.strava.server.remote.IRemoteFacade;
import es.deusto.ingenieria.sd.strava.server.remote.RemoteFacade;

public class MainProgram {

	public static void main(String[] args) {	
		//Activate Security Manager. It is needed for RMI.
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		String serverIP = args[0];
		int serverPort = Integer.parseInt(args[1]); 
		String serverName = args[2];

		//args[0] = RMIRegistry IP
		//args[1] = RMIRegistry Port
		//args[2] = Service Name
		//args[3] = meta ip
		//args[4] = meta port
		//args[5] = google ip
		//args[6] = google port
		//args[7] = google name


		String name = "//" + serverIP + ":" + serverPort + "/" + serverName;		

		//Bind remote facade instance to a service name using RMIRegistry
		try {
			IRemoteFacade remoteFacade = new RemoteFacade(args);	
			Naming.rebind(name, remoteFacade);
			System.out.println(" * Strava '" + name + "' started, congratulations!");
			//prueba(remoteFacade);
		} catch (Exception ex) {
			System.err.println(" # Strava Server Exception: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private static void prueba(IRemoteFacade remoteFacade) {
		try {
			long token;
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date d = format.parse ( "2009-12-31" );    
			token = remoteFacade.register(1,"oiermentxaka@opendeusto.es","7110eda4d09e062aa5e4a390b0a572ac0d2c0220","7110eda4d09e062aa5e4a390b0a572ac0d2c0220",d,1.1,11,21,31);
			remoteFacade.getUser(token);
			remoteFacade.logout(token);
			System.out.println("------");
			token = remoteFacade.register(1,"mail1@mail.com","7110eda4d09e062aa5e4a390b0a572ac0d2c0220","7110eda4d09e062aa5e4a390b0a572ac0d2c0220",d,1.1,11,21,31);
			remoteFacade.getUser(token);
			remoteFacade.logout(token);
			System.out.println("------");
			token = remoteFacade.register(2,"mail2@mail.com","7110eda4d09e062aa5e4a390b0a572ac0d2c0220","7110eda4d09e062aa5e4a390b0a572ac0d2c0220",d,1.2,12,22,32);				
			remoteFacade.getUser(token);
			remoteFacade.logout(token);
			System.out.println("------");
			token = remoteFacade.register(3,"mail3@mail.com","7110eda4d09e062aa5e4a390b0a572ac0d2c0220","7110eda4d09e062aa5e4a390b0a572ac0d2c0220",d,1.3,13,23,33);				
			remoteFacade.getUser(token);
			remoteFacade.createTrainingSession(token, "session1", "Bicycle" , 10.00, d, 15,30, 40.0);
			remoteFacade.createTrainingSession(token, "session2", "Running" , 14.00, d, 15,30, 50.0);
			remoteFacade.setupChallenge(token, "challenge1", d, d, 2.0, 45.0, "Bicycle");
			remoteFacade.setupChallenge(token, "challenge2", d, d, 4.0, 40.0, "Running");
			remoteFacade.logout(token);
			//Login
			System.out.println("--------IMPRIMO USUARIOS----------");
			for (User entry : UserDAO.getInstance().getAll()) {
				System.out.println(entry);
			}
			System.out.println("------------------");
			token = remoteFacade.login(1,"oiermentxaka@opendeusto.es","7110eda4d09e062aa5e4a390b0a572ac0d2c0220");		
			System.out.println(remoteFacade.getUser(token));
			remoteFacade.logout(token);
			System.out.println("------");
			token = remoteFacade.login(2,"oiermentxaka@opendeusto.es","7110eda4d09e062aa5e4a390b0a572ac0d2c0220");		
			System.out.println(remoteFacade.getUser(token));
			remoteFacade.logout(token);
			System.out.println("------");
			token = remoteFacade.login(3,"oiermentxaka@opendeusto.es","7110eda4d09e062aa5e4a390b0a572ac0d2c0220");
			System.out.println(remoteFacade.getUser(token));
			remoteFacade.logout(token);
			//Get user
			System.out.println("---getuser---");
			token = remoteFacade.login(1,"oiermentxaka@opendeusto.es","7110eda4d09e062aa5e4a390b0a572ac0d2c0220");		
			remoteFacade.getUser(token);
			//Create training session
			System.out.println("---createUsers---");
			remoteFacade.createTrainingSession(token, "session1", "Bicycle" , 10.00, d, 15,30, 40.0);
			remoteFacade.createTrainingSession(token, "session2", "Running" , 14.00, d, 15,30, 50.0);
			//Set up challenge
			System.out.println("---createChallenges---");
			remoteFacade.setupChallenge(token, "challenge1", d, d, 2.0, 45.0, "Bicycle");
			remoteFacade.setupChallenge(token, "challenge2", d, d, 4.0, 40.0, "Running");
			//Get challenges
			System.out.println("---getAllChallenges---");
			for(int i = 0; i<remoteFacade.getAllChallenges().size();i++) {
				System.out.println(remoteFacade.getAllChallenges().get(i).toString());
			}
			//Get trainings
			System.out.println("---getAllTrainigs---");
			for(int i = 0; i<remoteFacade.getAllTrainings().size();i++) {
				System.out.println(remoteFacade.getAllTrainings().get(i).toString());
			}	
			//Deslogueo
			remoteFacade.logout(token);
			System.out.println("Salgo de prueba");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
